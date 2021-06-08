package com.example.ecommerceapp.ui.main.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.repository.auth.AuthRepository
import com.example.ecommerceapp.repository.main.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartFragmentViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    authRepository: AuthRepository
) :
    ViewModel() {

    //    var product = MutableLiveData<List<CartProduct>>()
    var token = MutableLiveData<Boolean>()
    var netWorkStatus = MutableLiveData<Boolean>()

    init {
        refreshCartProducts()
        token.value = authRepository.checkIfAuthenticated()
        netWorkStatus.value = true
//        fetchCartItem()

    }
    var cartProducts = productRepository.cartProducts


    private fun refreshCartProducts() {
        viewModelScope.launch {
            productRepository.refreshCartProducts()
        }
    }

//
//    fun fetchCartItem() {
//        viewModelScope.launch {
//            val result = productRepository.fetchCartItems()
//            product.value = result
//        }
//    }

}