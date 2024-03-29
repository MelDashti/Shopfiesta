package com.example.ecommerceapp.repository.main

import androidx.lifecycle.LiveData
import com.example.ecommerceapp.api.main.responses.CartProduct
import com.example.ecommerceapp.api.main.responses.PostCartItemResponse
import com.example.ecommerceapp.api.main.responses.PostFavoriteItemResponse
import com.example.ecommerceapp.domain.Product
import com.example.ecommerceapp.persistence.NotificationItem
import com.example.ecommerceapp.util.FilterType


interface ProductRepository {
    val product: LiveData<List<Product>>
    suspend fun fetchProductInfo(productId: String): Product
    suspend fun removeCartProduct(productId: String)
    suspend fun removeFavProduct(productId: String)
    suspend fun addToCart(token: String): PostCartItemResponse
    fun searchProduct(query: String?): LiveData<List<Product>>
    suspend fun refreshProducts(): Unit
    fun applyFiltering(filterType: FilterType): List<Product>
    suspend fun addToFavorite(productId: String): PostFavoriteItemResponse
    val cartProducts: LiveData<List<CartProduct>>
    suspend fun refreshCartProducts()
    suspend fun refreshFavProducts()
    suspend fun reduceProductQuantity(productId: String)
    suspend fun checkIfFav(productId: String): Boolean

    val favProduct: LiveData<List<Product>>
    suspend fun increaseProductQuantity(productId: String): PostCartItemResponse
    suspend fun clearNotifications()

    val noCartItem: LiveData<Int>
    val notifications: LiveData<List<NotificationItem>>
}