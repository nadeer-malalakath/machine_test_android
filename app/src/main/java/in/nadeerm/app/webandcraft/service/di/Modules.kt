package `in`.nadeerm.app.webandcraft.service.di

import `in`.nadeerm.app.webandcraft.service.network.ApiHelper
import `in`.nadeerm.app.webandcraft.service.network.ApiService
import `in`.nadeerm.app.webandcraft.service.network.ApiServiceImpl
import `in`.nadeerm.app.webandcraft.service.repository.HomeDataRepository
import `in`.nadeerm.app.webandcraft.view.adapter.BannerTableViewAdapter
import `in`.nadeerm.app.webandcraft.view.adapter.CategoryTableViewAdapter
import `in`.nadeerm.app.webandcraft.view.adapter.ProductsTableViewAdapter
import `in`.nadeerm.app.webandcraft.viewmodel.BannerViewModel
import `in`.nadeerm.app.webandcraft.viewmodel.HomeDataViewModel
import `in`.nadeerm.app.webandcraft.viewmodel.MainViewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val NetworkModule = module {

    factory<ApiService> { (ApiServiceImpl()) }
    factory { ApiHelper(apiService = get()) }
}

val FactoryModule = module {

    factory { HomeDataRepository(apiHelper = get()) }

}

val ViewModelModule = module {

    viewModel { MainViewModel() }
    viewModel { HomeDataViewModel(homeDataRepository = get()) }
    viewModel { BannerViewModel(homeDataRepository = get()) }

}

val AdapterModule = module {
    factory { BannerTableViewAdapter() }
    factory { CategoryTableViewAdapter() }
    factory { ProductsTableViewAdapter() }
}

val appModules = listOf(NetworkModule, FactoryModule, ViewModelModule, AdapterModule)