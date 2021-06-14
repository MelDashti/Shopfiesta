package com.example.ecommerceapp.api.main.responses

import com.squareup.moshi.Json


class GetCartItemResponse2() {
    var authentication: String? = null
    var message: String? = null
    var error: Boolean? = null
    @Json(name = "product")
    var product: List<CartItem>? = null
}



class PostCartItemResponse() {
    var authentication: String? = null
    var message: String? = null
    var error: Boolean? = null
}

