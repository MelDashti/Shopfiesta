package com.example.ecommerceapp.api.auth

import com.example.Result
import com.example.ecommerceapp.api.auth.responses.MoshiResult
import com.example.ecommerceapp.util.BASE_URL2
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    this.level = HttpLoggingInterceptor.Level.BODY
}

val client: OkHttpClient = OkHttpClient.Builder().apply {
    this.addInterceptor(interceptor)
}.build()

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

var gson = GsonBuilder()
    .setLenient()
    .create()

private val retrofit2 =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson)).client(client)
        .baseUrl(BASE_URL2)
        .build()

object RegisterApi {
    val registerApiService: RegisterApiService by lazy {
        retrofit2.create(RegisterApiService::class.java)
    }
}

//
//data class Customer(
//    @Expose
//    @SerializedName("customer_id") val customerId: Int? = null,
//    @Expose
//    @SerializedName("first_name") val firstName: String,
//    @Expose
//    @SerializedName("last_name") val lastName: String,
//    @Expose
//    @SerializedName("email") val email: String,
//)
interface RegisterApiService {
    @FormUrlEncoded
    @POST("login.php")
    fun loginCustomer(
        @Field("email") email: String,
        @Field("password") password: String
    ): MoshiResult

    @FormUrlEncoded
    @POST("register.php")
    fun createCustomer(
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<Result>

}