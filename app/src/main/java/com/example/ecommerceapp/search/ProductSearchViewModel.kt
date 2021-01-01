package com.example.ecommerceapp.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.domain.Product
import com.example.ecommerceapp.repository.Repository
import com.example.ecommerceapp.util.FilterType
import kotlinx.coroutines.launch

class ProductSearchViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {

    lateinit var filterType: FilterType

    private val _searchList = repository.product
    val searchList: LiveData<List<Product>> = _searchList

    init {
        refreshEcomProducts()
    }

    private fun refreshEcomProducts() {
        viewModelScope.launch {
            repository.refreshProducts()
        }
    }

    override fun onCleared() {
        super.onCleared()
    }


}







