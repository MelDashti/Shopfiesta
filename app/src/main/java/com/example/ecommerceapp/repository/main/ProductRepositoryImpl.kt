package com.example.ecommerceapp.repository.main

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.ecommerceapp.api.main.EcomApiService
import com.example.ecommerceapp.api.main.asDatabaseModel
import com.example.ecommerceapp.api.main.responses.CartProduct
import com.example.ecommerceapp.api.main.responses.NetworkProduct
import com.example.ecommerceapp.api.main.responses.PostCartItemResponse
import com.example.ecommerceapp.api.main.responses.PostFavoriteItemResponse
import com.example.ecommerceapp.domain.Product
import com.example.ecommerceapp.persistence.ProductDao
import com.example.ecommerceapp.persistence.asDomainModel
import com.example.ecommerceapp.persistence.asDomainModel2
import com.example.ecommerceapp.util.FilterType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val ecomApiService: EcomApiService,
    private val productDao: ProductDao,
    private val sharedPreferences: SharedPreferences
) :
    ProductRepository {

    //ProductDatabase returns a live data so that our database is up to date. Now when we fetch this live data from room database
    // we wanna convert it to domain objects. Now we could have directly used asDomainObject extension function on a list of database products
    // but because it is contained in a live data object we can't.By using transformation maps we convert the DatabaseProduct Live data into Domain Model Live data.
    override val product: LiveData<List<Product>> =
        Transformations.map(productDao.getProducts()) { it.asDomainModel() }

    override fun applyFiltering(filterType: FilterType): List<Product> {
        var list1 = listOf<Product>()
        list1 = product.value!!
        val list2 = mutableListOf<Product>()
        if (filterType == FilterType.ACCESSORIES)
            list1.forEach {
                if (it.category == "electronics")
                    list2 += it
            }
        if (filterType == FilterType.LAPTOPS)
            list1.forEach {
                if (it.price < 800.0)
                    list2 += it
            }
        if (filterType == FilterType.PHONES)
            list1.forEach {
                if (it.price > 700)
                    list2 += it
            }
        if (filterType == FilterType.RECENTLY_VIEWED)
            list1.forEach {
                if (it.price < 700.0)
                    list2 += it
            }
        if (filterType == FilterType.POPULAR)
            list1.forEach {
                if (it.price >= 700.0)
                    list2 += it
            }
        return list2
    }


    //this will be the api used to refresh the offline cache
    override suspend fun refreshProducts() {
        //get back to this one **
        withContext(Dispatchers.IO) {
            val products = ecomApiService.getProperties()
            productDao.insertProducts(products.product.asDatabaseModel())
        }
    }


    override suspend fun fetchProductInfo(productId: String): Product {
        val productInfo: Product
        return withContext(Dispatchers.IO) {
            val cachedProductInfo = productDao.getSpecificProduct(productId)
            if (cachedProductInfo != null) {
                productInfo = cachedProductInfo.asDomainModel()
            } else {
                val product = productDao.getSpecificProduct(productId)
                productInfo = product.asDomainModel()
                productDao.insertProduct(product)
            }
            productInfo
        }
    }

    override suspend fun fetchFavoriteItems(): LiveData<List<Product>> {
        val result = ecomApiService.fetchFavoriteItems()
        val favItemLiveData: MutableLiveData<List<NetworkProduct>> = MutableLiveData()
        favItemLiveData.value = result.product
        val cartItems: LiveData<List<Product>> =
            Transformations.map(favItemLiveData) { it.asDomainModel2() }
//        Log.d("cart", result.product!!.asDomainModel2()[1].name)
        return cartItems
    }

    override suspend fun addToFavorite(productId: String): PostFavoriteItemResponse {
        val result = ecomApiService.postFavoriteItem(productId)
        Log.d("favorite", result.message.toString())
        return result
    }

    override suspend fun fetchCartItems(): List<CartProduct> {
        val result = ecomApiService.fetchCartItems()
//        val cartItemLiveData: MutableLiveData<List<NetworkProduct>> = MutableLiveData()
//        cartItemLiveData.value = result.product
//        val cartItems: LiveData<List<Product>> =
//            Transformations.map(cartItemLiveData) { it.asDomainModel2() }
//        Log.d("cart", result.product!!.asDomainModel2()[1].name)
        return if (result.product.isNullOrEmpty()) emptyList()
        else result.product!!
    }

    override suspend fun fetchNoOfCartItems(): Int {
        val result = ecomApiService.fetchCartItems()
        return if (result.product.isNullOrEmpty()) 0 else {
            var quantity: Int = 0
            result.product!!.forEach {
                quantity += it.quantity
            }
            quantity
        }

    }


    override suspend fun addToCart(productId: String): PostCartItemResponse {
        val result = ecomApiService.postCartItem(productId)
        Log.d("cart", result.message.toString())
        return result
    }

    override fun searchProduct(query: String?): LiveData<List<Product>> {
        return Transformations.map(productDao.getSearchResult(query)) { it.asDomainModel() }
    }
}