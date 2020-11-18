package com.dev777popov.materialapp.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev777popov.materialapp.R
import com.dev777popov.materialapp.databinding.ItemPhotoBinding

class PhotosAdapter(private val list: MutableList<String>) :
    RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPhotoBinding.bind(itemView)
        fun bind(item: String) {
            loadImage(binding.image, item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])


    private fun loadImage(img: ImageView, image: String) {
        Glide.with(img.context)
            .load(Uri.parse(image))
            .centerCrop()
            .fitCenter()
            .into(img)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}