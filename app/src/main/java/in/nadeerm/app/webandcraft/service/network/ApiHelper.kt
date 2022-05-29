package `in`.nadeerm.app.webandcraft.service.network


class ApiHelper(private val apiService: ApiService) {


    fun getBannersApiCall() = apiService.getBannersApiCall()
    fun getCategoryApiCall() = apiService.getCategoryApiCall()
    fun getProductsApiCall() = apiService.getProductsApiCall()


}