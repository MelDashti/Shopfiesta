package com.example.ecommerceapp.api.main.responses

import com.squareup.moshi.Json

class FavoritesResponse{
    var error: Boolean? = null
    var message: String? = null
    @Json(name = "product")
    var product: List<NetworkProduct>? = null
}