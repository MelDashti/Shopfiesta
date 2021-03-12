package com.example.ecommerceapp.repository.main

import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.Result
import com.example.ecommerceapp.api.auth.RegisterApi
import com.example.ecommerceapp.api.main.EcomApi
import com.example.ecommerceapp.api.main.asDatabaseModel
import com.example.ecommerceapp.domain.Product
import com.example.ecommerceapp.persistence.ProductDao
import com.example.ecommerceapp.persistence.asDomainModel
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject


class Repository @Inject constructor(private val productDao: ProductDao) {


    //aight team it's been a while time to start coding again
    //ProductDatabase returns a live data so that our database is up to date. Now when we fetch this live data from room database
    // we wanna convert it to domain objects. Now we could have directly used asDomainObject extension function on a list of database products
    // but because it is contained in a live data object we can't.By using transformation maps we convert the DatabaseProduct Live data into Domain Model Live data.
    val product: LiveData<List<Product>> =
        Transformations.map(productDao.getProducts()) { it.asDomainModel() }

    fun loginToMySql() {

    }

    // register
    fun register(fullname: String, email: String, password: String){
        val hey: Call<Result> =
            RegisterApi.registerApiService.createCustomer("Milad", fullname, email, password)

        hey.enqueue(object : retrofit2.Callback<Result> {
            override fun onResponse(
                call: Call<Result>,
                response: Response<Result>
            ) {
                if (response.isSuccessful && response.body()!!.error==false) {
                    Log.d("hey", response.body()!!.message!!)
                    Log.d("hey", response.body()!!.customer!!.email!!)
                } else
                    Log.d("hey", response.body()!!.message!!)
            }

            override fun onFailure(@NonNull call: Call<Result>, @NonNull t: Throwable) {
                Log.d("hey", t.localizedMessage)
            }
        })
    }

    //this will be the api used to refresh the offline cache
    suspend fun refreshProducts() {
        //get back to this one **
        val products = EcomApi.retrofitService.getProperties()
        productDao.insertProducts(products.asDatabaseModel())

    }

    suspend fun fetchProductInfo(productId: String): Product {
        val productInfo: Product
        val cachedProductInfo = productDao.getSpecificProduct(productId)
        if (cachedProductInfo != null) {
            productInfo = cachedProductInfo.asDomainModel()
        } else {
            val product = productDao.getSpecificProduct(productId)
            productInfo = product.asDomainModel()
            productDao.insertProduct(product)
        }
        return productInfo
    }

    fun searchProduct(query: String?): LiveData<List<Product>> {
        return Transformations.map(productDao.getSearchResult(query)) { it.asDomainModel() }
    }
}