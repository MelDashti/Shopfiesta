package com.example.ecommerceapp.ui.main.productinfo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.domain.Product
import com.example.ecommerceapp.repository.main.ProductRepository
import kotlinx.coroutines.launch

public class ProductInfoViewModel @ViewModelInject constructor(private val productRepository: ProductRepository) :
    ViewModel() {
    var product = MutableLiveData<Product?>()

    init {
        product.value = Product()
    }

    fun fetchProductInfo(string: String) {
        viewModelScope.launch {
            try {
                product.value = productRepository.fetchProductInfo(string)
            } catch (e: Exception) {

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}


