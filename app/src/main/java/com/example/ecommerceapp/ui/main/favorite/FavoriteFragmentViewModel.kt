package com.example.ecommerceapp.ui.main.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.domain.Product
import com.example.ecommerceapp.repository.main.ProductRepository

class FavoriteFragmentViewModel @ViewModelInject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    init {

    }

    val list = MutableLiveData<List<Product>>()

    override fun onCleared() {
        super.onCleared()
    }





}