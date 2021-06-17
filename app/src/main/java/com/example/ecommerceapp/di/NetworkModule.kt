package com.example.ecommerceapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.ecommerceapp.api.auth.RegisterApiService
import com.example.ecommerceapp.api.main.EcomApiService
import com.example.ecommerceapp.util.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences(
            PreferenceKeys.PREFERENCE_FILE_KEY,
            Context.MODE_PRIVATE
        )
    }

    @Singleton
    @Provides()
    fun provideAuthInterceptor(sharedPreferences: SharedPreferences): AuthInterceptor {
        return AuthInterceptor(sharedPreferences)
    }


    @Singleton
    @Provides()
    fun provideNetworkInterceptor(@ApplicationContext context: Context): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(context)
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): LoggingInterceptor {
        return LoggingInterceptor()
    }

    @Singleton
    @Provides
    fun provideRegisterApiService(retrofit: Retrofit): RegisterApiService {
        return retrofit.create(RegisterApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideEcomApiService(retrofit: Retrofit): EcomApiService {
        return retrofit.create(EcomApiService::class.java)
    }


    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(okHttpClient)
            .baseUrl(BASE_URL)
        return retrofit.build()
    }

    //For debugging purpose.
    // When using this interceptor add @header("Authorization") token: String to your get request
    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    //As soon as we move to production, it is recommended to turn off the logging, completely.

    @Singleton
    @Provides
    fun provideOkHttpClient(
        networkConnectionInterceptor: NetworkConnectionInterceptor,
        authInterceptor: AuthInterceptor, interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            this.addInterceptor(networkConnectionInterceptor).addInterceptor(authInterceptor)
                .addInterceptor(interceptor)
        }.build()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }


}