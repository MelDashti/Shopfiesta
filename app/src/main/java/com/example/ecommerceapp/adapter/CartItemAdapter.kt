package com.example.ecommerceapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.api.main.responses.CartProduct
import com.example.ecommerceapp.databinding.CartListItemBinding
import com.example.ecommerceapp.ui.main.cart.CartFragmentViewModel

class CartItemAdapter(val clickListener: CartItemListener, val viewModel: CartFragmentViewModel) :
    ListAdapter<CartProduct, CartItemAdapter.CartItemViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<CartProduct>() {
        override fun areItemsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
            //  the id is a string not an integer ughhhhhhhh
            return oldItem.id.toInt() == newItem.id.toInt()
        }

        override fun areContentsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {

//            return oldItem.description.equals(newItem.description) &&
//                    oldItem.price.toInt() == newItem.price.toInt() &&
//                    oldItem.imgSrcUrl.equals(newItem.imgSrcUrl) &&
//                    oldItem.category.equals(newItem.category) &&
//                    oldItem.name.equals(newItem.name)

            return oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CartListItemBinding.inflate(inflater, parent, false)
        return CartItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    fun insertItem(productId: String) {
        viewModel.insertItem(productId)
    }

    fun removeItem(position: Int, productId: String) {
        viewModel.removeCartItem(productId)
    }

    fun updateQuantity(productId: String) {
        viewModel.updateProductQuantity(productId)
    }

    inner class CartItemViewHolder(val bind: CartListItemBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun bind(
            product: CartProduct, position: Int
        ) {
            bind.product = product
            bind.clickListener = clickListener
            bind.increase.setOnClickListener {
                val r = bind.integerNumber.text.toString().toInt()
                bind.integerNumber.text = (r + 1).toString()
                insertItem(product.id)
            }

            bind.decrease.setOnClickListener {
                val r = bind.integerNumber.text.toString().toInt()
                if (r == 1) {
                    removeItem(position, product.id)
                }
                if (r > 1) {
                    bind.integerNumber.text = (r - 1).toString()
                    updateQuantity(product.id)
                }
            }

            bind.executePendingBindings()

        }
    }

}

class CartItemListener(val ClickListener: (productId: String) -> Unit) {
    fun onClick(product: CartProduct) = ClickListener(product.id)
}
