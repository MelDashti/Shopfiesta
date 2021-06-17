package com.example.ecommerceapp.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.repository.main.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {
    fun clearNotificationList() {
        viewModelScope.launch {
            repository.clearNotifications()
        }
    }

    val notificationList = repository.notifications


}