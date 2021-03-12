package com.example.ecommerceapp.ui.auth.login

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.repository.main.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragmentViewModel @ViewModelInject constructor(val repository: Repository) :
    ViewModel() {

//    var fullname = MutableLiveData<String>()
//    var email = MutableLiveData<String>()
//    var password = MutableLiveData<String>()


    private val _navigateToRegisterPage = MutableLiveData<Boolean>()
    val navigateToRegisterPage: LiveData<Boolean> = _navigateToRegisterPage

    fun onNavigateToRegister() {
        _navigateToRegisterPage.value = true
    }

    fun navigatedToLRegister() {
        _navigateToRegisterPage.value = false
    }


    fun loginToServer(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.login(email, password)
                if (!result.error!!) {
                    Log.d("hey", result.message!!)
                } else Log.d("hey", result.message!!)
            } catch (e: Exception) {
                Log.d("hey", e.localizedMessage)

            }
        }
    }
}


