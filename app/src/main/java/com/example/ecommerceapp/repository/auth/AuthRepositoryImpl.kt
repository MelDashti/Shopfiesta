package com.example.ecommerceapp.repository.auth

import android.content.SharedPreferences
import android.util.Log
import com.example.ecommerceapp.api.auth.RegisterApiService
import com.example.ecommerceapp.api.auth.responses.MoshiResult
import com.example.ecommerceapp.util.PreferenceKeys
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val registerApiService: RegisterApiService,
    private val sharedPreferences: SharedPreferences
) :
    AuthRepository {

    override suspend fun checkAuthenticationState(): String? {
        TODO("Not yet implemented")
    }

    override suspend fun login(email: String, password: String): MoshiResult {
        val result = registerApiService.loginCustomer(email, password)
        val token = result.token
        sharedPreferences.edit().putString(PreferenceKeys.PREFERENCE_AUTH_KEY, token).apply()
        sharedPreferences.edit().putString(PreferenceKeys.PREFERENCE_EMAIL_KEY, result.customer!!.email).apply()
        sharedPreferences.edit().putString(PreferenceKeys.PREFERENCE_AUTH_KEY, token).apply()
        return result
    }

    override suspend fun register(fullname: String, email: String, password: String): MoshiResult {
        val result = registerApiService.createCustomer("hey", fullname, email, password)
        val token = result.token
        Log.d("hello", token!!)
        sharedPreferences.edit().putString(PreferenceKeys.PREFERENCE_AUTH_KEY, token).apply()
        sharedPreferences.edit().putString(PreferenceKeys.PREFERENCE_EMAIL_KEY, result.customer!!.email).apply()
        sharedPreferences.edit().putString(PreferenceKeys.PREFERENCE_NAME_KEY, result.customer!!.lastName).apply()
        return result
    }

    override suspend fun logout() {
        sharedPreferences.edit().clear().commit()
    }

    override suspend fun updateAccountProperties() {
        TODO("Not yet implemented")
    }


}