package `in`.nadeerm.app.webandcraft.service.model.homedata.products

import `in`.nadeerm.app.webandcraft.service.model.homedata.category.Caategory

data class ProductsResponse  (

    val type:String,

    val values : ArrayList<Products>
)