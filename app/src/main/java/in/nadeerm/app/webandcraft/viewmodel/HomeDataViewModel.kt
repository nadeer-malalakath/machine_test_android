package `in`.nadeerm.app.webandcraft.viewmodel

import `in`.nadeerm.app.webandcraft.Utils.Resource
import `in`.nadeerm.app.webandcraft.service.model.homedata.HomeDataBanners
import `in`.nadeerm.app.webandcraft.service.repository.HomeDataRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent

class HomeDataViewModel(private val homeDataRepository: HomeDataRepository) : ViewModel(),
    KoinComponent {

    private val compositeDisposable = CompositeDisposable()
    private val banners = MutableLiveData<Resource<HomeDataBanners>>()

    fun getBanners() {
        banners.postValue(Resource.loading(null))
        compositeDisposable.add(
            homeDataRepository.getBannersApiCall()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    banners.postValue(Resource.success(response))
                }, {
                    banners.postValue(Resource.exception("AppConstant.ERROR_MSG"))
                })
        )
    }


    fun getResponse() : MutableLiveData<Resource<HomeDataBanners>> {
        return banners
    }

}