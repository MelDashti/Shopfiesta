package com.example.ecommerceapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.databinding.FavoriteListItemBinding
import com.example.ecommerceapp.domain.Product

public class FavoriteItemAdapter(val clickListener: FavoriteItemListener) :
    ListAdapter<Product, FavoriteItemViewHolder>(FavoriteItemDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteItemViewHolder {
        return FavoriteItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FavoriteItemViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class FavoriteItemDiffUtilCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}

class FavoriteItemViewHolder(val bind: FavoriteListItemBinding) :
    RecyclerView.ViewHolder(bind.root) {
    fun bind(
        product: Product,
        clickListener: FavoriteItemListener
    ) {
        bind.product = product
        bind.clickListener = clickListener
        bind.favoriteButton.setOnCheckedChangeListener{buttonView, isChecked ->
        }
    }

    companion object {
        fun from(parent: ViewGroup): FavoriteItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = FavoriteListItemBinding.inflate(inflater, parent, false)
            return FavoriteItemViewHolder(binding)
        }
    }
}

class FavoriteItemListener(val ClickListener: (productId: String) -> Unit) {
    fun onClick(product: Product) = ClickListener(product.id)
}