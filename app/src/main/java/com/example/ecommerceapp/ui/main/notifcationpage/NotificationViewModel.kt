package com.example.ecommerceapp.ui.main.notifcationpage

import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel(){}