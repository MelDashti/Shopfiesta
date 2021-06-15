package com.example.ecommerceapp.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.domain.Product
import com.example.ecommerceapp.repository.auth.AuthRepository
import com.example.ecommerceapp.repository.main.ProductRepository
import com.example.ecommerceapp.util.FilterType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val authRepository: AuthRepository
) :
    ViewModel() {

     var noOfCartItems = productRepository.noCartItem
//    val noOfCartItems: LiveData<Int>
//        get() = _noOfCartItems

    init {
//        fetchNoOfCartItems()
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

    fun fetchNoOfCartItems(){
        viewModelScope.launch {
//            _noOfCartItems.value = productRepository.getNoOfCartProducts()
        }
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