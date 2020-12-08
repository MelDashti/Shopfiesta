package com.example.ecommerceapp.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.ecommerceapp.domain.Models
import com.example.ecommerceapp.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @ViewModelInject constructor(private val repository: Repository) : ViewModel() {

    init {
        refreshEcomProducts()
    }


    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    val listResult = repository.products

    fun refreshEcomProducts() {
        viewModelScope.launch {
            repository.refreshProducts()
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}