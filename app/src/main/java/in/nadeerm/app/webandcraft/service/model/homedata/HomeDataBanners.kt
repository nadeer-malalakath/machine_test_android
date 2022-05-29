package `in`.nadeerm.app.webandcraft.service.model.homedata

import `in`.nadeerm.app.webandcraft.service.model.homedata.banners.BannersResponse


data class HomeDataBanners (

    val status : Boolean,

    val homeData : ArrayList<BannersResponse>

)