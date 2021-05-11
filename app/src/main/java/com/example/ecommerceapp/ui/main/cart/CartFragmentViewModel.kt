package com.example.ecommerceapp.ui.main.cart

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.domain.Product
import com.example.ecommerceapp.repository.main.ProductRepository
import kotlinx.coroutines.launch

class CartFragmentViewModel @ViewModelInject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    var product = MutableLiveData<List<Product>>()

    init {
        fetchCartItem()
    }

     fun fetchCartItem() {
        viewModelScope.launch {
            val result = productRepository.fetchCartItems()
            product.value = result
            Log.d("cart", product.value!![0].id)
        }
    }


}