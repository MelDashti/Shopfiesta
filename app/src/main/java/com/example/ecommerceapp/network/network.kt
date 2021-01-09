package com.example.ecommerceapp.network

import com.example.ecommerceapp.database.DatabaseProduct
import com.example.ecommerceapp.util.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory()).baseUrl(
            BASE_URL
        ).build()

interface EcomApiService {
    @GET("products")
    suspend fun getProperties(): List<NetworkProduct>
}

object EcomApi{
    val retrofitService: EcomApiService by lazy {
        retrofit.create(EcomApiService::class.java)
    }
}


data class NetworkProduct(
    val id: String,
    val price: Double,
    @Json(name = "img_src")
    val imgSrcUrl: String,
    val name: String,
    val category: String
)

fun List<NetworkProduct>.asDatabaseModel(): List<DatabaseProduct> {
    return map{
        DatabaseProduct(
            productId = it.id,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price,
            name = it.name,
            category = it.category
        )
    }
}







