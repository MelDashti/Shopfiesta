package com.example.ecommerceapp.util

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.adapter.ProductAdapter
import com.example.ecommerceapp.api.main.responses.CartProduct
import com.example.ecommerceapp.domain.Product

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

@BindingAdapter(value = ["token", "data"])
fun showOnlyWhenEmpty(view: ImageView, token: Boolean, data: List<CartProduct>?) {
    if (token) {
        view.visibility = when {
            data == null || data.isEmpty() -> View.VISIBLE
            else -> View.GONE
        }}else view.visibility = View.GONE

}

@BindingAdapter("showOnlyWhenEmpty2")
fun View.showOnlyWhenEmpty2(data: List<Product>?) {
    visibility = when {
        data == null || data.isEmpty() -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("onlyWhenNotAuthenticated")
fun View.onlyWhenNotAuthenticated(token: Boolean) {
    visibility = when {
        !token -> View.VISIBLE
        else -> View.GONE
    }

}