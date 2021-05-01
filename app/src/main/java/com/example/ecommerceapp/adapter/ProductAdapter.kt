package com.example.ecommerceapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.databinding.ProductListItemBinding
import com.example.ecommerceapp.domain.Product
import kotlinx.android.synthetic.main.product_list_item.view.*

class ProductAdapter(val clickListener: ProductListener) :
    ListAdapter<Product, ProductViewHolder>(ProductDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
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

class ProductViewHolder(val bind: ProductListItemBinding) : RecyclerView.ViewHolder(bind.root) {
    fun bind(product: Product, clickListener: ProductListener) {
        bind.clickListener = clickListener
        bind.product = product
//        bind.imageView.transitionName = product.id
        bind.textView3.text = product.name
    }

    companion object {
        fun from(parent: ViewGroup): ProductViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ProductListItemBinding.inflate(inflater, parent, false)
            return ProductViewHolder(binding)
        }
    }
}

class ProductListener(
    val ClickListener: (productId: String, imageView: ImageView ) -> Unit
) {
    fun onClick(product: Product, view: View) = ClickListener(product.id, view.imageView )

}