package com.example.ecommerceapp.di

import android.content.SharedPreferences
import com.example.ecommerceapp.api.auth.RegisterApiService
import com.example.ecommerceapp.persistence.ProductDao
import com.example.ecommerceapp.repository.auth.AuthRepository
import com.example.ecommerceapp.repository.auth.AuthRepositoryImpl
import com.example.ecommerceapp.repository.main.ProductRepository
import com.example.ecommerceapp.repository.main.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideProductRepository(productDao: ProductDao): ProductRepository {
        return ProductRepositoryImpl(productDao)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideAuthRepository(
        registerApiService: RegisterApiService,
        sharedPreferences: SharedPreferences
    ): AuthRepository {
        return AuthRepositoryImpl(registerApiService, sharedPreferences)
    }


}