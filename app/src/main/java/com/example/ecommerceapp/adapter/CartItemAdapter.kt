package com.example.ecommerceapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.databinding.CartListItemBinding
import com.example.ecommerceapp.domain.Product

public class CartItemAdapter(val clickListener: CartItemListener) :
    ListAdapter<Product, CartItemViewHolder>(CartItemDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        return CartItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class CartItemDiffUtilCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}

class CartItemViewHolder(val bind: CartListItemBinding) : RecyclerView.ViewHolder(bind.root) {
    fun bind(product: Product, clickListener: CartItemListener) {
        bind.product = product
        bind.clickListener = clickListener
        bind.increase.setOnClickListener{
            val r = bind.integerNumber.text.toString().toInt()
            bind.integerNumber.text= (r+1).toString()
        }
        bind.decrease.setOnClickListener{
            val r = bind.integerNumber.text.toString().toInt()
            if (r>0)
            bind.integerNumber.text= (r-1).toString()
        }

    }

    companion object {
        fun from(parent: ViewGroup): CartItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = CartListItemBinding.inflate(inflater, parent, false)
            return CartItemViewHolder(binding)
        }
    }
}

class CartItemListener(val ClickListener: (productId: String) -> Unit) {
    fun onClick(product: Product) = ClickListener(product.id)
}