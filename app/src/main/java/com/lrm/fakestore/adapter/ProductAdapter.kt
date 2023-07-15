package com.lrm.fakestore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lrm.fakestore.R
import com.lrm.fakestore.databinding.ListItemBinding
import com.lrm.fakestore.model.Product

class ProductAdapter(
    private val context: Context,
    private val onItemClicked: (Product) -> Unit
): ListAdapter<Product, ProductAdapter.ProductViewHolder>(DiffCallback) {

    class ProductViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, context: Context) {

            binding.apply {
                Glide.with(context).load(product.thumbnail)
                    .placeholder(R.drawable.loading_animation2).into(binding.productImage)
                productName.text = product.title
                productBrand.text = product.brand
                productPrice.text = "$ ${product.price}"
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.title == newItem.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = getItem(position)
        holder.bind(currentProduct, context)

        holder.itemView.setOnClickListener { onItemClicked(currentProduct) }
    }
}