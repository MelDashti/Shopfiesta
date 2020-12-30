package com.example.ecommerceapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.GroupItemBinding
import com.example.ecommerceapp.domain.Group

class GroupAdapter(
    val popularListAdapter: ProductAdapter,
    val recentlyViewedAdapter: ProductAdapter,
    val categoryListAdapter: CategoryItemAdapter
) :
    ListAdapter<Group, GroupAdapter.GroupItemViewHolder>(GroupDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupItemViewHolder {
        return GroupItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GroupItemViewHolder, position: Int) {
        holder.bind(
            getItem(position),
            popularListAdapter,
            recentlyViewedAdapter,
            categoryListAdapter
        )
    }


    class GroupDiffUtilCallback : DiffUtil.ItemCallback<Group>() {
        override fun areItemsTheSame(oldItem: Group, newItem: Group): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Group, newItem: Group): Boolean {
            return oldItem == newItem
        }
    }

    class GroupItemViewHolder(val bind: GroupItemBinding) : RecyclerView.ViewHolder(bind.root) {
        fun bind(
            group: Group,
            popularListAdapter: ProductAdapter,
            recentlyViewedAdapter: ProductAdapter,
            categoryListAdapter: CategoryItemAdapter
        ) {
            bind.group = group
            bind.groupItemRecyclerView.adapter = when (group.title) {
                "Category" -> categoryListAdapter
                "Recently Viewed" -> recentlyViewedAdapter
                else -> popularListAdapter
            }

            val dividerItemDecoration = DividerItemDecoration(
                bind.root.context,
                DividerItemDecoration.HORIZONTAL
            )
            dividerItemDecoration.setDrawable(
                ContextCompat.getDrawable(bind.root.context, R.drawable.divider)!!
            )
            bind.groupItemRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        //hey there long time no see what's poppin my g  see accuracy is the only way you can't achieve greatness with unnecessary and redundant practice.
        companion object {
            fun from(parent: ViewGroup): GroupItemViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = GroupItemBinding.inflate(inflater, parent, false)
                return GroupItemViewHolder(binding)
            }
        }
    }
}

//    private fun setPopularList(): ProductAdapter {
//            val productAdapter = ProductAdapter{ProductListener{
//
//            } } }
//
//    private fun setRecentlyViewedList(): ProductAdapter {
//
//    }
//
//    private fun setCategoryList(): CategoryItemAdapter {
//    }
