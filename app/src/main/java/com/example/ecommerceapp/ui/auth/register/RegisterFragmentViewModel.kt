package com.example.ecommerceapp.ui.auth.register

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
class RegisterFragmentViewModel @Inject constructor(
    val authRepository: AuthRepository
) :
    ViewModel() {

//    var fullname = MutableLiveData<String>()+
//    var email = MutableLiveData<String>()
//    var password = MutableLiveData<String>()

    private val _navigateToLoginPage = MutableLiveData<Boolean>()

    val navigateToLoginPage: LiveData<Boolean> = _navigateToLoginPage

    private val _response = MutableLiveData<AuthResult>()

    val response: LiveData<AuthResult>
        get() = _response


    fun onNavigateToLogin() {
        _navigateToLoginPage.value = true
    }

    fun navigatedToLogin() {
        _navigateToLoginPage.value = false
    }


    fun registerOnWebServer(fullname: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                _response.value = authRepository.register(fullname, email, password)
            }
            catch (e: Exception){
                Log.d("error", e.localizedMessage)
            }
        }
    }
    //but


    //    fun registerOnWebServer(fullname: String, email: String, password: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val result = authRepository.register(fullname, email, password)
//                if (!result.error!!) {
//                    Log.d("hey", result.message!!)
//                    Log.d("hey", result.token!!)
//                    Log.d("hey", result.customer!!.email!!)
//                } else
//                    Log.d("hey", result.message!!)
//
//            } catch (e: Exception) {
//                Log.d("hey", e.localizedMessage)
//
//            }
//        }
//    }


}