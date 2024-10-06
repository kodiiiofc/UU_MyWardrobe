package com.kodiiiofc.urbanuniversity.mywardrobe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ClothesAdapter(private val clothes: List<Cloth>) : RecyclerView.Adapter<ClothesAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageIV = itemView.findViewById<ImageView>(R.id.iv_item_image)
        val nameTV = itemView.findViewById<TextView>(R.id.tv_item_name)
        val descriptionTV = itemView.findViewById<TextView>(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val cloth = clothes[position]
        holder.nameTV.text = cloth.name
        holder.descriptionTV.text = cloth.description
        holder.imageIV.setImageResource(cloth.image)
    }

    override fun getItemCount() = clothes.size
}