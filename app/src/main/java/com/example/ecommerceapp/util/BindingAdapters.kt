package com.example.ecommerceapp.util

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.domain.Product
import com.example.ecommerceapp.home.ProductAdapter

//this is for the submit list function every time our list changes.
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, list: List<Product>?) {
    val adapter = recyclerView.adapter as ProductAdapter
    adapter.submitList(list)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri).into(imgView)
    }
}

// here we set drawable resource id which is an integer in android:src for image view using data binding
@BindingAdapter("android:src")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}