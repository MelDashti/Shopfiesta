package com.example.ecommerceapp.ui.main.cart

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.repository.main.ProductRepository

class CartFragmentViewModel @ViewModelInject constructor(private val productRepository: ProductRepository) :
    ViewModel() {


        var list = productRepository.product

        init {

        }









}