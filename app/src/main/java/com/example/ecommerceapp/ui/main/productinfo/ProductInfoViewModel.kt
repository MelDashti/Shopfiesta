package com.example.ecommerceapp.ui.main.productinfo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.api.main.responses.PostCartItemResponse
import com.example.ecommerceapp.domain.Product
import com.example.ecommerceapp.repository.auth.AuthRepository
import com.example.ecommerceapp.repository.main.ProductRepository
import kotlinx.coroutines.launch

public class ProductInfoViewModel @ViewModelInject constructor(
    private val productRepository: ProductRepository,
    private val authRepository: AuthRepository
) :
    ViewModel() {

    private val _noOfCartItems = MutableLiveData<Int>()
    val noOfCartItems: LiveData<Int>
        get() = _noOfCartItems

    val product = MutableLiveData<Product?>()

    private val _onClickCartButton = MutableLiveData<Boolean>()
    val onClickCartButton: LiveData<Boolean>
        get() = _onClickCartButton

    private val _networkResponse = MutableLiveData<PostCartItemResponse>()
    val networkResponse: LiveData<PostCartItemResponse>
        get() = _networkResponse

    private val _onClickAddedToCart = MutableLiveData<Boolean>()
    val onClickAddedToCart: LiveData<Boolean>
        get() = _onClickAddedToCart

    init {
        product.value = Product()
        fetchNoOfCartItems()
    }

    fun onClickCart() {
        _onClickCartButton.value = true
    }

    fun checkIfAuthenticated(): Boolean {
        return authRepository.checkIfAuthenticated()
    }

    fun fetchProductInfo(string: String) {
        viewModelScope.launch {
            product.value = productRepository.fetchProductInfo(string)
        }
    }

    private fun fetchNoOfCartItems() {
        viewModelScope.launch {
            _noOfCartItems.value = productRepository.fetchNoOfCartItems()
        }
    }

    fun addToCart() {
        if (authRepository.checkIfAuthenticated()) {
            viewModelScope.launch {
                val result = productRepository.addToCart(product.value!!.id)
                _networkResponse.value = result

            }
            _onClickAddedToCart.value = true
        } else {
            _onClickAddedToCart.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun addToFavorite() {
        if (authRepository.checkIfAuthenticated()) {
            viewModelScope.launch {
                val result = productRepository.addToFavorite(product.value!!.id)
            }

        } else {
            _onClickAddedToCart.value = false
        }
    }
}


