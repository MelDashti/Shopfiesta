package com.example.ecommerceapp.ui.main.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.domain.Product
import com.example.ecommerceapp.repository.auth.AuthRepository
import com.example.ecommerceapp.repository.main.ProductRepository
import kotlinx.coroutines.launch

class FavoriteFragmentViewModel @ViewModelInject constructor(
    private val productRepository: ProductRepository,
    val authRepository: AuthRepository
) :
    ViewModel() {

    init {
        fetchFavoriteItem()
    }

    var list = MutableLiveData<List<Product>>()
    var token = MutableLiveData<Boolean>()


    init {
        token.value = authRepository.checkIfAuthenticated()
        fetchFavoriteItem()
    }

    private fun fetchFavoriteItem() {
        viewModelScope.launch {
            val result = productRepository.fetchFavoriteItems()
            list.value= result
        }
    }
}