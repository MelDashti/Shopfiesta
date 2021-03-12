package com.example.ecommerceapp.ui.main.cart

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.repository.main.Repository

class CartFragmentViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {

        var list = repository.product

        init {

        }









}