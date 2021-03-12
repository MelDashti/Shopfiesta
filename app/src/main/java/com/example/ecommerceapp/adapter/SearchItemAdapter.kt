package com.example.ecommerceapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.databinding.SearchListItemBinding
import com.example.ecommerceapp.domain.Product

class SearchItemAdapter(val clickListener: SearchItemListener) :
    ListAdapter<Product, SearchItemViewHolder>(SearchItemDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        return SearchItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class SearchItemDiffUtilCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}

class SearchItemViewHolder(val bind: SearchListItemBinding) : RecyclerView.ViewHolder(bind.root) {
    fun bind(product: Product, clickListener: SearchItemListener) {
        bind.product = product
        bind.clickListener = clickListener
    }

    companion object {
        fun from(parent: ViewGroup): SearchItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = SearchListItemBinding.inflate(inflater, parent, false)
            return SearchItemViewHolder(binding)
        }
    }
}

class SearchItemListener(val ClickListener: (productId: String) -> Unit) {
    fun onClick(product: Product) = ClickListener(product.id)
}