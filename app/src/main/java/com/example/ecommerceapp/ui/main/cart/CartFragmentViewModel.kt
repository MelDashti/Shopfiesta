package com.example.ecommerceapp.ui.main.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.api.main.responses.CartProduct
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

    var product = MutableLiveData<List<CartProduct>>()
    var token = MutableLiveData<Boolean>()

    init {
        token.value = authRepository.checkIfAuthenticated()
        fetchCartItem()
    }

    fun fetchCartItem() {
        viewModelScope.launch {
            val result = productRepository.fetchCartItems()
            product.value = result
        }
    }

}