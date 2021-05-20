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
//    private val SIGN_IN_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ECommerceApp)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.navHostFragment)
        val graph = navController.navInflater.inflate(R.navigation.main_nav_graph)
        val sharedPreferences = this.getSharedPreferences(PreferenceKeys.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
        val defaultValue = "empty"
        val token = sharedPreferences.getString(PreferenceKeys.PREFERENCE_AUTH_KEY, defaultValue)

        if (token == null || token == "empty") {
            graph.startDestination = R.id.navigation
        } else{
            Log.d("token ",token)
            graph.startDestination = R.id.homeFragment
        }
        navController.graph = graph


//        val navController = findNavController(R.id.navHostFragment)
//        val graph = navController.navInflater.inflate(R.navigation.main_nav_graph)


    }


    private fun setNavHost() {
    }


//    private fun firebaseAuth() {
//        val providers = arrayListOf(
//            AuthUI.IdpConfig.EmailBuilder().build(),
//            AuthUI.IdpConfig.GoogleBuilder().build()
//        )

//        val auth =
//            AuthMethodPickerLayout.Builder(R.layout.auth_layout).setEmailButtonId(R.id.email_button)
//                .setGoogleButtonId(R.id.google_button).setAnonymousButtonId(R.id.guest_button)
//                .build()
//        startActivityForResult(
//            AuthUI.getInstance()
//                .createSignInIntentBuilder()
//                .setAvailableProviders(providers).setAuthMethodPickerLayout(auth)
//                .setTheme(R.style.Theme_ECommerceApp)
//                .build(),
//            SIGN_IN_REQUEST_CODE
//        )
//
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == SIGN_IN_REQUEST_CODE) {
//            val response = IdpResponse.fromResultIntent(data)
//            if (resultCode == Activity.RESULT_OK) {
//// User successfully signed in.
//            } else {
//// Sign in failed. If response is null, the user canceled the sign-in flow using the back button. Otherwise, check the error code and handle the error.
//            }
//        }
//    }


}