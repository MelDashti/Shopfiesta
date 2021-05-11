package com.example.ecommerceapp.ui.main.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.repository.main.ProductRepository

class FavoriteFragmentViewModel @ViewModelInject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    init {

    }
    val listResult = productRepository.product

    override fun onCleared() {
        super.onCleared()
    }


}