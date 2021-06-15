package com.example.ecommerceapp.ui.main.productinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.api.main.responses.PostCartItemResponse
import com.example.ecommerceapp.domain.Product
import com.example.ecommerceapp.repository.auth.AuthRepository
import com.example.ecommerceapp.repository.main.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductInfoViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val authRepository: AuthRepository
) :
    ViewModel() {

     var noOfCartItems = productRepository.noCartItem


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

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite

    init {

        refreshCartProducts()
        product.value = Product()
    }

    private fun refreshCartProducts() {
        viewModelScope.launch {
            productRepository.refreshCartProducts()
        }
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

    fun addToFavorite(productId: String) {
        if (authRepository.checkIfAuthenticated()) {
            viewModelScope.launch {
                productRepository.addToFavorite(productId)
            }
        } else {
            _onClickAddedToCart.value = false
        }
    }

    fun removeFavorite(productId: String) {
        viewModelScope.launch {
            productRepository.removeFavProduct(productId)
        }
    }

    fun checkIfFavorite(productId: String) {
        viewModelScope.launch {
            _isFavorite.value = productRepository.checkIfFav(productId)
        }
    }
}


