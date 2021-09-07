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
import kotlinx.android.synthetic.main.products_item.view.*

class ProductsAdapter(val context: Context, var products: List<Featured.Data.Product>): RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }

    interface OnClickListener {

        fun onClick(position: Int, model: Featured.Data.Product)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var image: ImageView = itemView.iv_product_image
        var name: TextView = itemView.tv_product_name
        var description: TextView = itemView.tv_product_description
        var healthBenefits: TextView = itemView.tv_product_health_benefits
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.products_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        Glide.with(holder.itemView.context).load("https://cdn.saintfarms.com/"+product.product_image).fitCenter().into(holder.image)
        holder.name.text = product.product_name
        holder.description.text = product.product_description
        holder.healthBenefits.text = product.product_health_benefits

        holder.itemView.setOnClickListener {
            if (onClickListener != null){
                onClickListener!!.onClick(position, product)
            }
        }

    }

    override fun getItemCount(): Int {
        return products.size
    }
}