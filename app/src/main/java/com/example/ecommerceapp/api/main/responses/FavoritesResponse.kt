package com.example.ecommerceapp.api.main.responses

import com.squareup.moshi.Json

class FavoritesResponse {
    var authentication: String? = null
    var message: String? = null
    var error: Boolean? = null
    @Json(name = "product")
    var product: List<FavItem>? = null
}

class PostFavoriteItemResponse {
    var authentication: String? = null
    var message: String? = null
    var error: Boolean? = null
}