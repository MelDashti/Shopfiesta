package com.example.ecommerceapp.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.android.firebaseui_login_sample.FirebaseUserLiveData

class LoginViewModel : ViewModel() {

    val authenticationState = FirebaseUserLiveData().map { user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }

    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATION
    }


    override fun onCleared() {
        super.onCleared()
    }


}