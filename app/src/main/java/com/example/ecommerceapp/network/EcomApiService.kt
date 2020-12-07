package com.example.ecommerceapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET


private const val BASE_URL = "https://my-json-server.typicode.com/meldashti/ecom-products-api/"


private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
        BASE_URL
    ).build()

interface EcomApiService {
    @GET("products")
    suspend fun getProperties(): List<EcomProduct>
}


object EcomApi {
    val retrofitService: EcomApiService by lazy {
        retrofit.create(EcomApiService::class.java)
    }
}





