package com.example.ecommerceapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Room.databaseBuilder
import com.example.ecommerceapp.domain.Product

@Entity(tableName = "product_table")
data class DatabaseProduct constructor(
    @PrimaryKey
    val id: String,
    val imgSrcUrl: String,
    val price: Double,
    val name: String,
    val category: String
)

@Dao
interface ProductDao {

    @Query("select*from product_table where name like '%' || :value || '%' ")
    fun getSearchResult(value: String?): LiveData<List<DatabaseProduct>>

    @Query("select  * from product_table where id = :productId")
    fun getSpecificProduct(productId: String): DatabaseProduct

    @Query("select * from product_table")
    fun getProducts(): LiveData<List<DatabaseProduct>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(products: List<DatabaseProduct>)
}

@Database(entities = [DatabaseProduct::class], version = 2, exportSchema = true)
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

// here we create an extension function to convert database object to domain object. Domain object is used to update or change the ui.
fun List<DatabaseProduct>.asDomainModel(): List<Product> {
    return map {
        Product(
            id = it.id,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price,
            name = it.name,
            category = it.category
        )
    }
}

fun DatabaseProduct.asDomainModel() = Product(
    imgSrcUrl = imgSrcUrl,
    id = id,
    price = price,
    name = name,
    category = category
)



