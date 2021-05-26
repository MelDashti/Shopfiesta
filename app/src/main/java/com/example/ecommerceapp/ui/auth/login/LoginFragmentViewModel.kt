package com.example.ecommerceapp.ui.auth.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.api.auth.responses.AuthResult
import com.example.ecommerceapp.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(val authRepository: AuthRepository) :
    ViewModel() {

//    var fullname = MutableLiveData<String>()
//    var email = MutableLiveData<String>()
//    var password = MutableLiveData<String>()


    private val _response = MutableLiveData<AuthResult>()
    val response: LiveData<AuthResult>
        get() = _response

    private val _navigateToRegisterPage = MutableLiveData<Boolean>()
    val navigateToRegisterPage: LiveData<Boolean> = _navigateToRegisterPage

    fun onNavigateToRegister() {
        _navigateToRegisterPage.value = true
    }

    fun navigatedToLRegister() {
        _navigateToRegisterPage.value = false
    }


    fun loginToServer(email: String, password: String) {
        viewModelScope.launch {
            try {
                _response.value = authRepository.login(email, password)
            } catch (e: Exception) {
                Log.d("hey", e.localizedMessage)
            }
        }
    }
}


