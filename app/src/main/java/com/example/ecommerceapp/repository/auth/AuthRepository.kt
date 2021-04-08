package com.example.ecommerceapp.repository.auth

import com.example.ecommerceapp.api.auth.responses.MoshiResult

interface AuthRepository {
    suspend fun checkAuthenticationState(): String?
    suspend fun login(email: String, password: String): MoshiResult
    suspend fun register(fullname: String, email: String, password: String): MoshiResult
    suspend fun logout()
    suspend fun updateAccountProperties() : Unit
}