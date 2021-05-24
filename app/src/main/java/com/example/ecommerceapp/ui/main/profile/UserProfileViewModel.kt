package com.example.ecommerceapp.ui.main.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.repository.auth.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserProfileViewModel @ViewModelInject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    // still have to figure out the saving user in database or not part

    fun onNavigateToLauncherFragment() {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.logout()
        }
    }

    init {

    }


    fun logout() {

    }

    suspend fun updateAccountProperties() {
        authRepository.updateAccountProperties()
    }


}