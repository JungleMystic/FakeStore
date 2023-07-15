package com.lrm.fakestore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lrm.fakestore.R
import com.lrm.fakestore.databinding.ImageListItemBinding

class ImageAdapter(
    val context: Context,
    val imagesList: List<String>
    ): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(private val binding: ImageListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

            val image = binding.productImages
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ImageListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = imagesList[position]
        Glide.with(context).load(image).placeholder(R.drawable.loading_animation2).into(holder.image)
    }
}