package com.example.ecommerceapp.api.main

import com.example.ecommerceapp.api.GenericResponse
import com.example.ecommerceapp.api.main.responses.*
import com.example.ecommerceapp.persistence.DatabaseProduct
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface EcomApiService {
    @GET("getproductinfo.php")
    suspend fun getProperties(): ProductResponse

    @GET("getfavoriteitems.php")
    suspend fun fetchFavoriteItems(): FavoritesResponse

    @GET("getcartitems.php")
    suspend fun fetchCartItems(): GetCartItemResponse

    @FormUrlEncoded
    @POST("postfavoriteitem.php")
    suspend fun postFavoriteItem(@Field("product_id") productID: String): PostFavoriteItemResponse

    @FormUrlEncoded
    @POST("postcartitem.php")
    suspend fun postCartItem(@Field("product_id") productID: String): PostCartItemResponse

    @FormUrlEncoded
    @POST("updatequantity.php")
    suspend fun updateQuantity(@Field("product_id") productID: String): GenericResponse

    @FormUrlEncoded
    @POST("removecartitem.php")
    suspend fun removeCartItem(@Field("product_id") productID: String): GenericResponse

    @FormUrlEncoded
    @POST("removefavitem.php")
    suspend fun removeFavItem(@Field("product_id") productID: String): GenericResponse

}

fun List<NetworkProduct>?.asDatabaseModel(): List<DatabaseProduct> {
    return this!!.map {
        DatabaseProduct(
            productId = it.id,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price,
            name = it.name,
            category = it.category,
            description = it.description,
            favorite = it.favorite
        )
    }
}