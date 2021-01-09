package com.example.ecommerceapp.cart

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.repository.Repository

class CartFragmentViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {

        var list = repository.product

        init {

        }









}