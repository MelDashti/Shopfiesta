package com.example.ecommerceapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.databinding.CategoryListBinding
import com.example.ecommerceapp.domain.Category

class CategoryItemAdapter(val clickListener: CategoryListener) :
    RecyclerView.Adapter<CategoryViewHolder>() {
    var data = listOf<Category>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(data[position], clickListener)
    }
}


class CategoryViewHolder(val bind: CategoryListBinding) : RecyclerView.ViewHolder(bind.root) {
    fun bind(category: Category, clickListener: CategoryListener) {
        bind.clickListener = clickListener
        bind.category = category
    }


    companion object {
        fun from(parent: ViewGroup): CategoryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = CategoryListBinding.inflate(inflater, parent, false)
            return CategoryViewHolder(binding)
        }
    }
}

class CategoryListener(val ClickListener: (productId: Long) -> Unit) {
    fun onClick(category: Category) = ClickListener(category.id)
}