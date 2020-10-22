package com.example.ecommerceapp.authentication

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentLoginBinding
import com.firebase.ui.auth.AuthMethodPickerLayout
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val SIGN_IN_REQUEST_CODE = 1

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val bind = FragmentLoginBinding.inflate(inflater)
        firebaseAuth()
        return bind.root
    }

    private fun firebaseAuth() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        val auth =
            AuthMethodPickerLayout.Builder(R.layout.auth_layout).setEmailButtonId(R.id.email_button)
                .setGoogleButtonId(R.id.google_button).setAnonymousButtonId(R.id.guest_button)
                .build()
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers).setAuthMethodPickerLayout(auth)
                .setTheme(R.style.Theme_ECommerceApp)
                .build(),
            SIGN_IN_REQUEST_CODE
        )

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_REQUEST_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
// User successfully signed in.
            } else {
// Sign in failed. If response is null, the user canceled the sign-in flow using the back button. Otherwise, check the error code and handle the error.
            }
        }
    }

}