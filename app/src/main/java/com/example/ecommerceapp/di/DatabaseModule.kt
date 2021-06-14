package com.example.ecommerceapp.di

import android.app.Application
import com.example.ecommerceapp.persistence.ProductDao
import com.example.ecommerceapp.persistence.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideProductDao(database: ProductDatabase): ProductDao {
        return database.productDao
    }
    @Provides
    @Singleton
    fun provideDatabase(application: Application): ProductDatabase {
        return ProductDatabase.getInstance(application)
    }

}