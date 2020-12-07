package com.example.ecommerceapp.network

import com.squareup.moshi.Json

data class EcomProduct(
    val id: String,
    val price: Double,
    @Json(name = "img_src")
    val imgSrcUrl: String,
    val name: String,
    val category: String
)


        