package com.example.ecommerceapp.ui.home

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.example.ecommerceapp.network.EcomApi
import com.example.ecommerceapp.network.EcomProduct
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    init {
        getEcomProducts()
    }

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _listResult = MutableLiveData<List<EcomProduct>>()
    val listResult: LiveData<List<EcomProduct>>
        get() = _listResult

    fun getEcomProducts() {
        viewModelScope.launch {
            try {

                var list1 = EcomApi.retrofitService.getProperties()
                _listResult.value = list1
                Toast.makeText(getApplication<Application>().applicationContext,list1[1].name,Toast.LENGTH_LONG).show()
                    "Success: ${listResult.value?.size} Ecom properties retrieved"
            } catch (e: Exception) {
                Toast.makeText(getApplication<Application>().applicationContext,e.message,Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}