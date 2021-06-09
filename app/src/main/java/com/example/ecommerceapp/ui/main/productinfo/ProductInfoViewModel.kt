package com.example.ecommerceapp.ui.main.productinfo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.api.main.responses.PostCartItemResponse
import com.example.ecommerceapp.domain.Product
import com.example.ecommerceapp.repository.auth.AuthRepository
import com.example.ecommerceapp.repository.main.ProductRepository
import com.example.ecommerceapp.util.NetworkConnectionInterceptor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ProductInfoViewModel @Inject constructor(
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

    private val _favoriteButtonFilled = MutableLiveData<Boolean>()
    val favoriteButtonFilled: LiveData<Boolean>
        get() = _favoriteButtonFilled

    init {
        _favoriteButtonFilled.value = true
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
//            productRepository.checkIfProductIsFavorite(string)
        }
    }

    private fun fetchNoOfCartItems() {
        viewModelScope.launch {
            try {
                _noOfCartItems.value = productRepository.fetchNoOfCartItems()
            } catch (e: IOException) {
                if (e is NetworkConnectionInterceptor.NoConnectionException)
                    Log.d("Nooo", "No internet  connection")
            }
        }
    }

    fun addToCart() {
        if (authRepository.checkIfAuthenticated()) {
            viewModelScope.launch {
                val result = productRepository.addToCart(product.value!!.id)
                _networkResponse.value = result
            }
            _onClickAddedToCart.value = true
            _noOfCartItems.value = _noOfCartItems.value!! + 1
        } else {
            _onClickAddedToCart.value = false
        }
    }

    fun addToFavorite() {
        if (authRepository.checkIfAuthenticated()) {
            viewModelScope.launch {
                productRepository.addToFavorite(product.value!!.id)
            }

        } else {
            _onClickAddedToCart.value = false
        }
    }
}


