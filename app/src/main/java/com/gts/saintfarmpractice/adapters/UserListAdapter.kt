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
import com.gts.saintfarmpractice.models.WebUser
import kotlinx.android.synthetic.main.userlist_item.view.*

class UserListAdapter(val context: Context, var webUsers: ArrayList<WebUser>): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private val allWebUsers = ArrayList<WebUser>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var id: TextView = itemView.tv_userId
        var image: ImageView = itemView.iv_image
        var firstName: TextView = itemView.tv_firstName
        var lastName: TextView = itemView.tv_lastName
        var email: TextView = itemView.tv_email
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.userlist_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = allWebUsers[position]
        holder.id.text = user.id.toString()
        holder.firstName.text = user.first_name
        holder.lastName.text = user.last_name
        holder.email.text = user.email
        Glide.with(holder.itemView.context).load(user.avatar).into(holder.image)
    }

    fun updateList(newList: List<WebUser>){
        allWebUsers.clear()
        allWebUsers.addAll(newList)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return webUsers.size
    }
}