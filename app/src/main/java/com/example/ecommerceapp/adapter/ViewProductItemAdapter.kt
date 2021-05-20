package com.example.ecommerceapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.databinding.ViewProductsListItemsBinding
import com.example.ecommerceapp.domain.Product

public class ViewProductItemAdapter(val clickListenerView: ViewProductItemListener) :
    ListAdapter<Product, ViewProductViewHolder>(ViewProductItemDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewProductViewHolder {
        return ViewProductViewHolder.from(parent)
    }

    override fun onBindViewHolder(holderView: ViewProductViewHolder, position: Int) {
        holderView.bind(getItem(position), clickListenerView)
    }
}

class ViewProductItemDiffUtilCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}

class ViewProductViewHolder(val bind: ViewProductsListItemsBinding) :
    RecyclerView.ViewHolder(bind.root) {
    fun bind(product: Product, clickListenerView: ViewProductItemListener) {
        bind.product = product
        bind.clickListener = clickListenerView


    }

    companion object {
        fun from(parent: ViewGroup): ViewProductViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ViewProductsListItemsBinding.inflate(inflater, parent, false)
            return ViewProductViewHolder(binding)
        }
    }
}

class ViewProductItemListener(val ClickListener: (productId: String) -> Unit) {
    fun onClick(product: Product) = ClickListener(product.id)
}