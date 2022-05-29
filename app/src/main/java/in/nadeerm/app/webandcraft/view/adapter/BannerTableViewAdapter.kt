package `in`.nadeerm.app.webandcraft.view.adapter

import `in`.nadeerm.app.webandcraft.R
import `in`.nadeerm.app.webandcraft.Utils.AppConstant
import `in`.nadeerm.app.webandcraft.service.model.homedata.banners.Banners
import `in`.nadeerm.app.webandcraft.view.callback.EmptyViewHolder
import `in`.nadeerm.app.webandcraft.view.ui.banner.BannerFragment
import `in`.nadeerm.app.webandcraft.view.ui.base.BaseViewHolder
import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class BannerTableViewAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val banners = ArrayList<Banners>()
    private var listener: BannerListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            AppConstant.VIEW_TYPE_NORMAL -> ViewHolder(
                (LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_category_two, parent, false))
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
        return if (banners.size > 0) {
            banners.size
        } else {
            1
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (banners.isNotEmpty()) {
            AppConstant.VIEW_TYPE_NORMAL
        } else {
            AppConstant.VIEW_TYPE_EMPTY
        }
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder(itemView) {
        private val bannerImg = itemView.findViewById<CircleImageView>(R.id.banner_imagetwo)
        private val bannerTitle = itemView.findViewById<TextView>(R.id.category_name)
        private val cardColor = itemView.findViewById<CardView>(R.id.category_card)
        @SuppressLint("ResourceType")
        override fun onBind(position: Int) {
            super.onBind(position)
            try {
                val banners = banners[position]

                Picasso.get().load(banners.image_url).into(bannerImg)
                bannerTitle.text = banners.name

                if(position == 0) {
                    cardColor.setCardBackgroundColor(Color.parseColor("#f9e8e8"))
                }
                else if(position == 1)
                {
                    cardColor.setCardBackgroundColor(Color.parseColor("#f6f3c8"))
                }
                else if(position == 2)
                {
                    cardColor.setCardBackgroundColor(Color.parseColor("#fcf0f0"))
                }
                else if(position == 3)
                {
                    cardColor.setCardBackgroundColor(Color.parseColor("#e8dcf4"))
                }
                else if(position == 4)
                {
                    cardColor.setCardBackgroundColor(Color.parseColor("#fff2d9"))
                }



            } catch (e: Exception){}
        }

        override fun clear() {

        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addItems(list: ArrayList<Banners>) {
        banners.clear()
        banners.addAll(list)
        notifyDataSetChanged()
    }

    interface BannerListener{

    }

    fun setBannerListener(context: BannerFragment?){
        listener = context
    }
}