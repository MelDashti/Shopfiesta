package com.example.ecommerceapp.ui.auth.register

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.repository.main.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterFragmentViewModel @ViewModelInject constructor(val repository: Repository) :
    ViewModel() {

//    var fullname = MutableLiveData<String>()
//    var email = MutableLiveData<String>()
//    var password = MutableLiveData<String>()


    private val _navigateToLoginPage = MutableLiveData<Boolean>()
    val navigateToLoginPage: LiveData<Boolean> = _navigateToLoginPage

    fun onNavigateToLogin() {
        _navigateToLoginPage.value = true
    }

    fun navigatedToLogin() {
        _navigateToLoginPage.value = false
    }

    fun registerOnWebServer(fullname: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("hey","hdafdadf")
                val hey = repository.register(fullname, email, password)

            } catch (e: Exception) {
            }
        }
    }


}