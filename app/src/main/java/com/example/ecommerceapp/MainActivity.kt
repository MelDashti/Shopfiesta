package com.example.ecommerceapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.ecommerceapp.util.PreferenceKeys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ECommerceApp)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.navHostFragment)
        val graph = navController.navInflater.inflate(R.navigation.main_nav_graph)
        val sharedPreferences =
            this.getSharedPreferences(PreferenceKeys.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
        val defaultValue = "empty"
        val token = sharedPreferences.getString(PreferenceKeys.PREFERENCE_AUTH_KEY, defaultValue)

        if (token == null || token == "empty") {
            graph.startDestination = R.id.navigation
        } else {
            Log.d("token ", token)
            graph.startDestination = R.id.homeFragment
        }
        navController.graph = graph

    }}