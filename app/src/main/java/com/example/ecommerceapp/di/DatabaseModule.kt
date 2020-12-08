package com.example.ecommerceapp.di

import android.app.Application
import com.example.ecommerceapp.database.ProductDao
import com.example.ecommerceapp.database.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent


@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideProductDao(database: ProductDatabase): ProductDao {
        return database.productDao
    }

    @Provides
    fun provideDatabase(
        application: Application,
    ): ProductDatabase {
        return ProductDatabase.getInstance(application)
    }

}