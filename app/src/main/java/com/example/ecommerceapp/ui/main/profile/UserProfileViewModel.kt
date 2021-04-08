package com.example.ecommerceapp.ui.main.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.repository.auth.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserProfileViewModel @ViewModelInject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    // still have to figure out the saving user in database or not part

    private val _navigateToLauncherFragment = MutableLiveData<Boolean>()
    val navigateToLauncherFragment: LiveData<Boolean> = _navigateToLauncherFragment

    fun onNavigateToLauncherFragment() {
        _navigateToLauncherFragment.value = true
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.logout()
        }
    }

    init {

    }

    fun getUserInfo() {

    }

    suspend fun logout() {

    }

    suspend fun updateAccountProperties() {
        authRepository.updateAccountProperties()
    }


}