package com.example.ecommerceapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.databinding.CategoryListBinding
import com.example.ecommerceapp.network.Product


public class ProductAdapter() : ListAdapter<Product, ProductViewHolder>(ProductDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.bind(getItem(position))

    }

}


class ProductDiffUtilCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}


class ProductViewHolder(val bind: CategoryListBinding) : RecyclerView.ViewHolder(bind.root) {
    fun bind(product: Product) {
        bind.product = product
    }


    companion object {
        fun from(parent: ViewGroup): ProductViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = CategoryListBinding.inflate(inflater, parent, false)
            return ProductViewHolder(binding)
        }
    }


}



