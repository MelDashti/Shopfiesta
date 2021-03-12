package com.example.ecommerceapp.api.auth.responses

import com.squareup.moshi.Json


class MoshiResult {
    @Json(name = "error")
    var error: Boolean? = null

    @Json(name = "message")
    var message: String? = null

    @Json(name = "customer")
    var customer: Customer? = null
}


class Customer {
    @Json(name = "first_name")
    var firstName: String? = null

    @Json(name = "last_name")
    var lastName: String? = null

    @Json(name = "email")
    var email: String? = null

    @Json(name = "customer_id")
    var customerId: Int? = null
}
