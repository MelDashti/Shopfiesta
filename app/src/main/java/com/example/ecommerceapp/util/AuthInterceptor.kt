package com.example.ecommerceapp.util

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

public class AuthInterceptor(private val sharedPreferences: SharedPreferences): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        // If token has been saved, add it to the header
        // Hey there long time no see what's poppin you
        val token = sharedPreferences.getString(PreferenceKeys.PREFERENCE_AUTH_KEY,"empty")
            requestBuilder.addHeader("Authorization","Bearer $token")
        return chain.proceed(requestBuilder.build())
    }}