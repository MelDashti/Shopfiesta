package com.example.ecommerceapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EcomAppAplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}