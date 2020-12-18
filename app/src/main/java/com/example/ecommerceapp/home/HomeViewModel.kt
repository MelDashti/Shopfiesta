package com.example.ecommerceapp.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.domain.Product
import com.example.ecommerceapp.repository.Repository
import com.example.ecommerceapp.util.FilterType
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(private val repository: Repository) : ViewModel() {

    init {
        refreshEcomProducts()
    }

    val listResult = repository.product

    fun applyFiltering(filter: FilterType): List<Product> {
        var list1 = listOf<Product>()
        list1 = listResult.value!!
        val list2 = mutableListOf<Product>()
        if (filter == FilterType.RECENTLY_VIEWED)
            list1.forEach {
                if (it.price < 700.0)
                    list2 += it
            }
        if (filter == FilterType.POPULAR)
            list1.forEach {
                if (it.price>=700.0)
                    list2+= it
            }
        return list2
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