package com.example.ecommerceapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecommerceapp.authentication.SIGN_IN_REQUEST_CODE
import com.firebase.ui.auth.AuthMethodPickerLayout
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse

class MainActivity : AppCompatActivity() {
//
//    private val SIGN_IN_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        firebaseAuth()

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