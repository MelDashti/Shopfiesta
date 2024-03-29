package com.example.ecommerceapp.ui.main.search

import androidx.lifecycle.*
import com.example.ecommerceapp.domain.Product
import com.example.ecommerceapp.repository.main.ProductRepository
import com.example.ecommerceapp.util.FilterType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductSearchViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    lateinit var filterType: FilterType
    private val _query = MutableLiveData<String>()

    //
    private val _startSearch = MutableLiveData<Boolean>()
    val startSearch: LiveData<Boolean> = _startSearch
    val searchResultList: LiveData<List<Product>> = Transformations.switchMap(_query, ::filterIt)

    init {
        _startSearch.value = false
        searchNow("")
    }

    fun startSearch() {
        _startSearch.value = true
    }


    private fun filterIt(query: String?) = productRepository.searchProduct(query)


    fun searchNow(query: String?) {
        _query.value = query!!
    }

    private fun refreshEcomProducts() {
        viewModelScope.launch {
            productRepository.refreshProducts()
        }
    }


}







