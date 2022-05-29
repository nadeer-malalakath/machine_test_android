package `in`.nadeerm.app.webandcraft.service.network

import `in`.nadeerm.app.webandcraft.service.model.homedata.banners.HomeDataBanners
import `in`.nadeerm.app.webandcraft.service.model.homedata.category.HomeDataCategory
import `in`.nadeerm.app.webandcraft.service.model.homedata.products.HomeDataProducts
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET(ApiEndPoints.API_URL)
    fun getBannersApiCall(): Single<HomeDataBanners>

    @GET(ApiEndPoints.API_URL)
    fun getCategoryApiCall(): Single<HomeDataCategory>

    @GET(ApiEndPoints.API_URL)
    fun getProductsApiCall(): Single<HomeDataProducts>
}