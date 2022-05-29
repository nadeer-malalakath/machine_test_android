package `in`.nadeerm.app.webandcraft.view.adapter

import `in`.nadeerm.app.webandcraft.R
import `in`.nadeerm.app.webandcraft.Utils.AppConstant
import `in`.nadeerm.app.webandcraft.service.model.homedata.category.Caategory
import `in`.nadeerm.app.webandcraft.service.model.homedata.products.Products
import `in`.nadeerm.app.webandcraft.view.callback.EmptyViewHolder
import `in`.nadeerm.app.webandcraft.view.ui.banner.BannerFragment
import `in`.nadeerm.app.webandcraft.view.ui.base.BaseViewHolder
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductsTableViewAdapter   : RecyclerView.Adapter<BaseViewHolder>() {

    private val products = ArrayList<Products>()
    private var listener: ProductsListener? = null

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

        override fun onBind(position: Int) {
            super.onBind(position)
            try {
                val products = products[position]
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

    }

    fun setProductsListener(context: BannerFragment?){
        listener = context
    }
}