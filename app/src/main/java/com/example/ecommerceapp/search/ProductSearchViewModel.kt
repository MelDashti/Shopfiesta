package com.example.ecommerceapp.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.ecommerceapp.domain.Product
import com.example.ecommerceapp.repository.Repository
import com.example.ecommerceapp.util.FilterType
import kotlinx.coroutines.launch

class ProductSearchViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {

    lateinit var filterType: FilterType
    private val _query = MutableLiveData<String>()


    private val _startSearch = MutableLiveData<Boolean>()
    val startSearch: LiveData<Boolean> = _startSearch

    init {
        _startSearch.value = false
    }

    public fun startSearch() {
        _startSearch.value = true
    }


    private fun filterIt(query: String?) = repository.searchProduct(query)

    val searchResultList: LiveData<List<Product>> = Transformations.switchMap(_query, ::filterIt)

    fun searchNow(query: String?) {
        _query.value = query
    }

    init {
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







