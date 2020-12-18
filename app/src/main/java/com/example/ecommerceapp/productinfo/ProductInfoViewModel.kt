package com.example.ecommerceapp.productinfo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.domain.Product
import com.example.ecommerceapp.repository.Repository
import kotlinx.coroutines.launch

public class ProductInfoViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {
    var product = MutableLiveData<Product?>()
    init {
        product.value = Product()
    }
    fun getProduct(string: String) {
        viewModelScope.launch {
            product.value = repository.getSpecificProduct(string)
        }
    }
    override fun onCleared() {
        super.onCleared()
    }
}


