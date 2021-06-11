package com.example.ecommerceapp.ui.main.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.repository.auth.AuthRepository
import com.example.ecommerceapp.repository.main.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteFragmentViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    val authRepository: AuthRepository
) :
    ViewModel() {

    var list = productRepository.favProduct
    var token = MutableLiveData<Boolean>()

    init {
        refreshFavoriteItems()
        token.value = authRepository.checkIfAuthenticated()
    }

    private fun refreshFavoriteItems() {
        viewModelScope.launch {
            productRepository.refreshFavProducts()
        }
    }

    fun removeFavItem(productId: String) {
        viewModelScope.launch {
            productRepository.removeFavProduct(productId)
        }


    }
}