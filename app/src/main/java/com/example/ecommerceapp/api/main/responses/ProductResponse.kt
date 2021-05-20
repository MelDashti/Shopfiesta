package com.example.ecommerceapp.api.main.responses

import com.squareup.moshi.Json

class ProductResponse {
    var error: Boolean? = null
    var message: String? = null

    @Json(name = "product")
    var product: List<NetworkProduct>? = null
}

//    @Retention(RUNTIME)
//    @JsonQualifier
//    annotation class Wrapped
//
//    class ProductJsonConverter {
//        @Wrapped
//        @FromJson
//        fun fromJson(json: ProductResponse): List<NetworkProduct>? {
//            return json.product!!
//        }
//
//        @ToJson
//        fun toJson(@Wrapped value: List<NetworkProduct>?): ProductResponse {
//            throw UnsupportedOperationException()
//        }
//    }



data class CartProduct(
    @Json(name = "quantity")
    val quantity: Int,
    @Json(name = "product_name")
    val name: String,
    @Json(name = "unit_price")
    val price: Double,
    @Json(name = "product_id")
    val id: String,
    @Json(name = "img_src_url")
    val imgSrcUrl: String,
    @Json(name = "category")
    val category: String,
    @Json(name = "description")
    val description: String
)

    data class NetworkProduct(
        @Json(name = "product_name")
        val name: String,
        @Json(name = "unit_price")
        val price: Double,
        @Json(name = "product_id")
        val id: String,
        @Json(name = "img_src_url")
        val imgSrcUrl: String,
        @Json(name = "category")
        val category: String,
        @Json(name = "description")
        val description: String
    )