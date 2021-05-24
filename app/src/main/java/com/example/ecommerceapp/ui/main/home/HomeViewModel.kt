package com.example.ecommerceapp.ui.main.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.domain.Product
import com.example.ecommerceapp.repository.auth.AuthRepository
import com.example.ecommerceapp.repository.main.ProductRepository
import com.example.ecommerceapp.util.FilterType
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val productRepository: ProductRepository,
    private val authRepository: AuthRepository
) :
    ViewModel() {


    init {
        refreshEcomProducts()
    }

    val listResult = productRepository.product


    private val _navigateToUserProfile = MutableLiveData<Boolean>()
    val navigateToUserProfile: LiveData<Boolean> = _navigateToUserProfile


    fun checkIfAuthenticated():Boolean {
        return authRepository.checkIfAuthenticated()
    }

    fun goToUserProfile() {
        _navigateToUserProfile.value = true
    }

    fun navigatedToUserProfile() {
        _navigateToUserProfile.value = false
    }

    fun applyFiltering(filter: FilterType): List<Product> {
        return productRepository.applyFiltering(filter)
    }


    private fun refreshEcomProducts() {
        viewModelScope.launch {
            productRepository.refreshProducts()
        }
    }

}