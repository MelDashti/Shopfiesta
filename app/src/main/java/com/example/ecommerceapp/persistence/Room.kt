package com.example.ecommerceapp.persistence

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Room.databaseBuilder
import com.example.ecommerceapp.api.main.responses.NetworkProduct
import com.example.ecommerceapp.domain.Product

//@Entity(tableName = "user_table")
//data class User constructor(
//    @PrimaryKey
//    val userId: Double,
//    val cartItem: Double
//)

@Entity(tableName = "product_table")
data class DatabaseProduct constructor(
    @PrimaryKey
    val productId: String,
    val imgSrcUrl: String,
    val price: Double,
    val name: String,
    val category: String,
    val description: String
)


@Dao
interface ProductDao {

    @Query("select*from product_table where name like '%' || :value || '%' ")
    fun getSearchResult(value: String?): LiveData<List<DatabaseProduct>>

    @Query("select  * from product_table where productId = :productId")
    fun getSpecificProduct(productId: String): DatabaseProduct

    @Query("select * from product_table")
    fun getProducts(): LiveData<List<DatabaseProduct>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(products: List<DatabaseProduct>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: DatabaseProduct)
}

@Database(entities = [DatabaseProduct::class], version = 8, exportSchema = true)
abstract class ProductDatabase : RoomDatabase() {
    abstract val productDao: ProductDao

    companion object {
        @Volatile
        private var INSTANCE: ProductDatabase? = null
        fun getInstance(context: Context): ProductDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = databaseBuilder(
                        context.applicationContext,
                        ProductDatabase::class.java,
                        "sleep_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}


fun List<NetworkProduct>.asDomainModel2(): List<Product> {
    return map {
        Product(
            id = it.id,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price,
            name = it.name,
            category = it.category,
            description = it.description

        )
    }
}


// here we create an extension function to convert database object to domain object. Domain object is used to update or change the ui.
fun List<DatabaseProduct>.asDomainModel(): List<Product> {
    return map {
        Product(
            id = it.productId,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price,
            name = it.name,
            category = it.category,
            description = it.description
        )
    }
}


fun DatabaseProduct.asDomainModel() = Product(
    imgSrcUrl = imgSrcUrl,
    id = productId,
    price = price,
    name = name,
    category = category,
    description = description
)



