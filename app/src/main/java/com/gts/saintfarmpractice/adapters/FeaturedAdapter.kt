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
import kotlinx.android.synthetic.main.featured_item.view.*

class FeaturedAdapter(val context: Context, var featured: List<Featured.Data>): RecyclerView.Adapter<FeaturedAdapter.ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }

    interface OnClickListener {

        fun onClick(position: Int, model: List<Featured.Data.Product>)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.tv_featuredCardTitle
        var image: ImageView = itemView.iv_featuredCardImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.featured_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feature = featured[position]
        holder.title.text = feature.title
        Glide.with(holder.itemView.context).load("https://cdn.saintfarms.com/"+feature.image).fitCenter().into(holder.image)

        holder.itemView.setOnClickListener {
            if (onClickListener != null){
                onClickListener!!.onClick(position, featured[position].product)
            }
        }

    }

    override fun getItemCount(): Int {
        return featured.size
    }
}