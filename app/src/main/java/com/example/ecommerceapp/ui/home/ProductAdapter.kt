package com.example.ecommerceapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.databinding.CategoryListBinding
import com.example.ecommerceapp.domain.Models
import com.example.ecommerceapp.domain.Models.Products
import com.example.ecommerceapp.network.NetworkProduct


public class ProductAdapter() :
    ListAdapter<Products, ProductViewHolder>(ProductDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class ProductDiffUtilCallback : DiffUtil.ItemCallback<Products>() {
    override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem == newItem
    }
}


class ProductViewHolder(val bind: CategoryListBinding) : RecyclerView.ViewHolder(bind.root) {
    fun bind(product: Products) {
        bind.product = product
        bind.textView3.text = product.name
    }


    companion object {
        fun from(parent: ViewGroup): ProductViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = CategoryListBinding.inflate(inflater, parent, false)
            return ProductViewHolder(binding)
        }
    }


}



