package com.example.ecommerceapp.repository.auth

import com.example.ecommerceapp.api.auth.responses.AuthResult

interface AuthRepository {
     fun checkIfAuthenticated(): Boolean
    suspend fun login(email: String, password: String): AuthResult
    suspend fun register(fullname: String, email: String, password: String): AuthResult
    suspend fun logout()
    suspend fun updateAccountProperties() : Unit
}