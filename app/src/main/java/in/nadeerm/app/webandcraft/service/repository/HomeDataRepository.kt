package `in`.nadeerm.app.webandcraft.service.repository

import `in`.nadeerm.app.webandcraft.service.model.homedata.banners.HomeDataBanners
import `in`.nadeerm.app.webandcraft.service.model.homedata.category.HomeDataCategory
import `in`.nadeerm.app.webandcraft.service.model.homedata.products.HomeDataProducts
import `in`.nadeerm.app.webandcraft.service.network.ApiHelper
import io.reactivex.Single
import org.koin.core.KoinComponent

class HomeDataRepository(private val apiHelper: ApiHelper) : KoinComponent {

    fun getBannersApiCall(): Single<HomeDataBanners> {
        return apiHelper.getBannersApiCall()
    }

    fun getCategoryApiCall(): Single<HomeDataCategory> {
        return apiHelper.getCategoryApiCall()
    }

    fun getProductsApiCall(): Single<HomeDataProducts> {
        return apiHelper.getProductsApiCall()
    }
}