package com.example.ecommerceapp.ui.main.list

import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.domain.Product
import com.example.ecommerceapp.repository.main.ProductRepository
import com.example.ecommerceapp.util.FilterType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {


    init {

    }

    val listResult = productRepository.product

    override fun onCleared() {
        super.onCleared()
    }

    fun applyFiltering(filter: FilterType): List<Product> {
        return productRepository.applyFiltering(filter)
    }


}