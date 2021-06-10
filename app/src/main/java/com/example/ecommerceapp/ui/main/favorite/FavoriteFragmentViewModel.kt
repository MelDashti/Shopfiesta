package com.example.ecommerceapp.ui.main.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.domain.Product
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

    var list = MutableLiveData<List<Product>>()
    var token = MutableLiveData<Boolean>()

    init {
        token.value = authRepository.checkIfAuthenticated()
        fetchFavoriteItem()
    }

    private fun fetchFavoriteItem() {
        viewModelScope.launch {
            val result = productRepository.fetchFavoriteItems()
            list.value = result
        }
    }

    fun removeFavItem(productId: String) {
        viewModelScope.launch {
           productRepository.removeFavProduct(productId)
        }


    }
}