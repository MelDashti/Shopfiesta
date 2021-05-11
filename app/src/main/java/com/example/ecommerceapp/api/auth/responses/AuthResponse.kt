package com.example.ecommerceapp.api.auth.responses

import com.squareup.moshi.Json

class AuthResult {
    var error: Boolean? = null

    var message: String? = null

    @Json(name = "customer")
    var customer: Customer? = null

    var token: String? = null
}


data class Customer(
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String,
    var email: String,
    @Json(name = "customer_id")
    val customerId: Int,
    )
