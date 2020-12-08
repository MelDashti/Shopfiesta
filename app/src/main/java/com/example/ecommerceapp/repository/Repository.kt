package com.example.ecommerceapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.ecommerceapp.database.ProductDao
import com.example.ecommerceapp.database.asDomainModel
import com.example.ecommerceapp.domain.Models
import com.example.ecommerceapp.domain.Models.Products
import com.example.ecommerceapp.network.EcomApi
import com.example.ecommerceapp.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class Repository @Inject constructor(private val productDao: ProductDao) {

    //ProductDatabase returns a live data so that our database is up to date. Now when we fetch this live data from room database
    // we wanna convert it to domain objects. Now we could have directly used asDomainObject extension function on a list of database products
    // but because it is contained in a live data object we can't.By using transformation maps we convert the DatabaseProduct Live data into Domain Model Live data.

    val products: LiveData<List<Products>> = Transformations.map(productDao.getProducts()) {
        it.asDomainModel()
    }

    //this will be the api used to refresh the offline cache
    suspend fun refreshProducts() {
        withContext(Dispatchers.IO) {
            val products = EcomApi.retrofitService.getProperties()
            productDao.insertProducts(products.asDatabaseModel())
        }
    }


}