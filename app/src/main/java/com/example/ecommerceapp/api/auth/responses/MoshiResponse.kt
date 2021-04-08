package com.example.ecommerceapp.api.auth.responses

import com.example.Customer
import com.squareup.moshi.Json

class MoshiResult {
    var error: Boolean? = null

    var message: String? = null

    @Json(name = "customer")
    var customer: Customer? = null

    var token: String? = null
}

//data class Customer {
//    @Json(name = "first_name")
//    val firstName: String? = null
//
//    @Json(name = "last_name")
//    val lastName: String? = null
//
//    var email: String? = null
//
//    @Json(name = "customer_id")
//    val customerId: Int? = null
//}
data class Customer(
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String,
    val email: String,
    @Json(name = "customer_id")
    val customerId: Int
)