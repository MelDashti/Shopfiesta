package com.example.ecommerceapp.domain

import com.squareup.moshi.Json

class Models {

    data class Products(
        val imgSrcUrl: String,
        val id: String,
        val price: Double,
        val name: String,
        val category: String
    )

}