package com.gts.saintfarmpractice.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gts.saintfarmpractice.R
import com.gts.saintfarmpractice.models.Featured
import com.gts.saintfarmpractice.models.ShopByCategory
import kotlinx.android.synthetic.main.featured_item.view.*
import kotlinx.android.synthetic.main.shop_by_category_item.view.*

class ShopByCategoryAdapter(val context: Context, var shopByCategory: List<ShopByCategory.Data>): RecyclerView.Adapter<ShopByCategoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.tv_shopByCategoryCardTitle
        var image: ImageView = itemView.iv_shopByCategoryCardImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.shop_by_category_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sbc = shopByCategory[position]
        holder.title.text = sbc.title
        Glide.with(holder.itemView.context).load("https://cdn.saintfarms.com/"+sbc.featured_banner).fitCenter().into(holder.image)
    }

    override fun getItemCount(): Int {
        return shopByCategory.size
    }
}