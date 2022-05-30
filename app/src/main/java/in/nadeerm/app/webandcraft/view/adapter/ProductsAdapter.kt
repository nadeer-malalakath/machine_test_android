package `in`.nadeerm.app.webandcraft.view.adapter

import `in`.nadeerm.app.webandcraft.R
import `in`.nadeerm.app.webandcraft.Utils.AppConstant
import `in`.nadeerm.app.webandcraft.service.model.homedata.products.Products
import `in`.nadeerm.app.webandcraft.view.callback.EmptyViewHolder
import `in`.nadeerm.app.webandcraft.view.ui.HomeFragment
import `in`.nadeerm.app.webandcraft.view.ui.base.BaseViewHolder
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductsAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val products = ArrayList<Products>()
    private var listener: ProductsListener? = null
    var favourites : MutableList<String> = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            AppConstant.VIEW_TYPE_NORMAL -> ViewHolder(
                (LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_products, parent, false))
            )
            AppConstant.VIEW_TYPE_EMPTY -> EmptyViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_empty_view, parent, false)
            )
            else -> EmptyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_empty_view,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return if (products.size > 0) {
            products.size
        } else {
            1
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (products.isNotEmpty()) {
            AppConstant.VIEW_TYPE_NORMAL
        } else {
            AppConstant.VIEW_TYPE_EMPTY
        }
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder(itemView) {
        private val bannerImg = itemView.findViewById<ImageView>(R.id.product_image)
        private val offerBanner = itemView.findViewById<TextView>(R.id.offer_banner)
        private val expressBanner = itemView.findViewById<LinearLayout>(R.id.express_banner)
        private val productActualPrice = itemView.findViewById<TextView>(R.id.product_actual_price)
        private val productOfferPrice = itemView.findViewById<TextView>(R.id.product_offer_price)
        private val productName = itemView.findViewById<TextView>(R.id.product_name)
        private val favouriteBtn = itemView.findViewById<TextView>(R.id.favourite_btn)
        override fun onBind(position: Int) {
            super.onBind(position)
            try {
                val products = products[position]
                favourites.add(position,"false")
                productActualPrice.text = products.actual_price
                productActualPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                productOfferPrice.text = products.offer_price
                productName.text = products.name
                if(products.is_express == true)
                {
                    expressBanner.visibility = View.VISIBLE
                }
                else
                {
                    expressBanner.visibility = View.INVISIBLE
                }
                if(products.offer > 0)
                {
                    offerBanner.visibility = View.VISIBLE
                    offerBanner.text = "${products.offer.toString()}% OFF"
                }
                else
                {
                    offerBanner.visibility = View.INVISIBLE
                }
                if (products.actual_price == products.offer_price)
                {
                    productActualPrice.visibility = View.INVISIBLE
                }
                favouriteBtn.setOnClickListener {
                    if (favourites[position] == "false")
                    {
                        favourites.set(position,"true")
                        favouriteBtn.setBackgroundResource(R.drawable.ic_marked_favourite)
                        listener?.favourite(products.name)
                    }
                    else
                    {
                        favourites.set(position,"false")
                        favouriteBtn.setBackgroundResource(R.drawable.ic_unmarked_favourite)
                        listener?.unfavourite(products.name)
                    }

                }


                Picasso.get().load(products.image).into(bannerImg)

            } catch (e: Exception){}
        }

        override fun clear() {

        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addItems(list: ArrayList<Products>) {
        products.clear()
        products.addAll(list)
        notifyDataSetChanged()
    }

    interface ProductsListener{

        fun favourite(name:String)
        fun unfavourite(name:String)

    }

    fun setProductsListener(context: HomeFragment?){
        listener = context
    }
}