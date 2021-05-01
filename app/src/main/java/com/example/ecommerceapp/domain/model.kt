package com.example.ecommerceapp.domain
import androidx.room.PrimaryKey
import com.example.ecommerceapp.util.FilterType

//data class User(
//    val fullname: String?,
//    val email: String?,
//    val password: String?
//)

data class Product(
    val imgSrcUrl: String = "",
    val id: String = "",
    val price: Double = 0.0,
    val name: String = "",
    val category: String = ""
)

data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val filterType: FilterType,
    val drawable: Int
)

data class Group(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val filterType: FilterType,
    val title: String,
//    val productList: List<Product>,
//    val categoryList: List<Category>
)
