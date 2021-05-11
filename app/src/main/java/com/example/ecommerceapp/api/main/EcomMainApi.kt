package com.example.ecommerceapp.api.main

import com.example.ecommerceapp.api.main.responses.GetCartItemResponse
import com.example.ecommerceapp.api.main.responses.NetworkProduct
import com.example.ecommerceapp.api.main.responses.PostCartItemResponse
import com.example.ecommerceapp.api.main.responses.ProductResponse
import com.example.ecommerceapp.persistence.DatabaseProduct
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface EcomApiService {
    @GET("getproductinfo.php")
    suspend fun getProperties(): ProductResponse

    @GET("getcartitems.php")
    suspend fun fetchCartItems(): GetCartItemResponse

    @FormUrlEncoded
    @POST("postcartitem.php")
    suspend fun postCartItem(@Field("product_id") productID: String): PostCartItemResponse
}


fun List<NetworkProduct>?.asDatabaseModel(): List<DatabaseProduct> {
    return this!!.map {
        DatabaseProduct(
            productId = it.id,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price,
            name = it.name,
            category = it.category,
            description = it.description
        )
    }
}