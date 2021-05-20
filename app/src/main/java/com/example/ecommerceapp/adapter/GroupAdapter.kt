package com.example.ecommerceapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.GroupListItemBinding
import com.example.ecommerceapp.domain.Group
import com.example.ecommerceapp.util.FilterType

class GroupAdapter(
    val clickListener: GroupItemListener,
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
            categoryListAdapter, clickListener
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

    class GroupItemViewHolder(val bind: GroupListItemBinding) : RecyclerView.ViewHolder(bind.root) {
        fun bind(
            group: Group,
            popularListAdapter: ProductAdapter,
            recentlyViewedAdapter: ProductAdapter,
            categoryListAdapter: CategoryItemAdapter,
            clickListener: GroupItemListener
        ) {
            bind.group = group
            bind.clickListener = clickListener
            bind.groupItemRecyclerView.adapter = when (group.title) {
                "Category" -> {
                    bind.constraintLayout.isVisible = false
                    categoryListAdapter
                }
                "Recently Viewed" -> recentlyViewedAdapter
                else -> popularListAdapter
            }

            bind.groupItemRecyclerView.scheduleLayoutAnimation()
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
                val binding = GroupListItemBinding.inflate(inflater, parent, false)
                binding.viewAllButton.setOnClickListener {
                }
                return GroupItemViewHolder(binding)
            }
        }
    }
}

class GroupItemListener(
    val ClickListener: (filterType: FilterType) -> Unit
) {
    fun onClick(group: Group) = ClickListener(group.filterType)
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
