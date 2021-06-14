package com.example.ecommerceapp.repository.auth

import android.content.SharedPreferences
import android.util.Log
import com.example.ecommerceapp.api.auth.RegisterApiService
import com.example.ecommerceapp.api.auth.responses.AuthResult
import com.example.ecommerceapp.persistence.ProductDao
import com.example.ecommerceapp.util.PreferenceKeys
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val registerApiService: RegisterApiService,
    private val sharedPreferences: SharedPreferences
) :
    AuthRepository {

    override fun checkIfAuthenticated(): Boolean {
        val token = sharedPreferences.getString(PreferenceKeys.PREFERENCE_AUTH_KEY, "empty")
        return !(token == null || token == "empty")
    }

    override suspend fun login(email: String, password: String): AuthResult {
        val result = registerApiService.loginCustomer(email, password)
        if (!result.error!!) {
            val token = result.token
            Log.d("name", result.customer!!.lastName.toString())
            sharedPreferences.edit().putString(PreferenceKeys.PREFERENCE_AUTH_KEY, token).apply()
            sharedPreferences.edit()
                .putString(PreferenceKeys.PREFERENCE_EMAIL_KEY, result.customer!!.email).apply()
            sharedPreferences.edit()
                .putString(PreferenceKeys.PREFERENCE_NAME_KEY, result.customer!!.lastName).apply()
        }
        return result
    }

    override suspend fun register(fullname: String, email: String, password: String): AuthResult {
        val result = registerApiService.createCustomer("hey", fullname, email, password)

        if (!result.error!!) {
            val token = result.token
            sharedPreferences.edit().putString(PreferenceKeys.PREFERENCE_AUTH_KEY, token).apply()
            sharedPreferences.edit()
                .putString(PreferenceKeys.PREFERENCE_EMAIL_KEY, result.customer!!.email).apply()
            sharedPreferences.edit()
                .putString(PreferenceKeys.PREFERENCE_NAME_KEY, result.customer!!.lastName).apply()
        }
        return result
    }

    override suspend fun logout() {
        productDao.let {
            it.clearCartItems()
            it.clearFavItems()
        }

        sharedPreferences.edit().clear().apply()

    }

    override suspend fun updateAccountProperties() {
        TODO("Not yet implemented")
    }


}