package `in`.nadeerm.app.webandcraft.service.model.homedata.products

data class Products (

    val id : Int,

    val name : String,

    val image : String,

    val actual_price : String,

    val offer_price : String,

    val offer : Int,

    val is_express : Boolean
)