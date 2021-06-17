package com.example.ecommerceapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.databinding.NotificationListItemBinding
import com.example.ecommerceapp.persistence.NotificationItem

public class NotificationAdapter :
    ListAdapter<NotificationItem, NotificationItemViewHolder>(NotificationItemDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationItemViewHolder {
        return NotificationItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NotificationItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class NotificationItemDiffUtilCallback : DiffUtil.ItemCallback<NotificationItem>() {
    override fun areItemsTheSame(oldItem: NotificationItem, newItem: NotificationItem): Boolean {
        return oldItem.id.toInt() == newItem.id.toInt()
    }

    override fun areContentsTheSame(oldItem: NotificationItem, newItem: NotificationItem): Boolean {
        return oldItem == newItem
    }
}

class NotificationItemViewHolder(val bind: NotificationListItemBinding) :
    RecyclerView.ViewHolder(bind.root) {
    fun bind(
        notificationItem: NotificationItem
    ) {
        bind.notification = notificationItem
    }

    companion object {
        fun from(parent: ViewGroup): NotificationItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = NotificationListItemBinding.inflate(inflater, parent, false)
            return NotificationItemViewHolder(binding)
        }
    }
}
