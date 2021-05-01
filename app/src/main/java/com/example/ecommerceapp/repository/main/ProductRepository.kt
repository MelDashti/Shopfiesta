package com.example.ecommerceapp.repository.main

import androidx.lifecycle.LiveData
import com.example.ecommerceapp.domain.Product
import com.example.ecommerceapp.util.FilterType

interface ProductRepository {
    val product: LiveData<List<Product>>
    suspend fun fetchProductInfo(productId: String): Product
    fun searchProduct(query: String?): LiveData<List<Product>>
    suspend fun refreshProducts(): Unit
    fun applyFiltering(filterType: FilterType): List<Product>
}