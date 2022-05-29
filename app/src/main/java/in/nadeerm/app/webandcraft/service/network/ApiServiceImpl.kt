package `in`.nadeerm.app.webandcraft.service.network

import `in`.nadeerm.app.webandcraft.service.model.homedata.banners.HomeDataBanners
import `in`.nadeerm.app.webandcraft.service.model.homedata.category.HomeDataCategory
import `in`.nadeerm.app.webandcraft.service.model.homedata.products.HomeDataProducts
import io.reactivex.Single

class ApiServiceImpl: ApiService {
    override fun getBannersApiCall(): Single<HomeDataBanners> {
        return ApiClient.instance.getBannersApiCall()
    }

    override fun getCategoryApiCall(): Single<HomeDataCategory> {
        return ApiClient.instance.getCategoryApiCall()
    }

    override fun getProductsApiCall(): Single<HomeDataProducts> {
        return ApiClient.instance.getProductsApiCall()
    }

}