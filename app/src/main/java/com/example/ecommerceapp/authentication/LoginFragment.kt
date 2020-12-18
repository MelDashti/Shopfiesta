package com.example.ecommerceapp.authentication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.ecommerceapp.R
import com.firebase.ui.auth.AuthMethodPickerLayout
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

private const val SIGN_IN_REQUEST_CODE = 1

class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observeAuthenticatoinState()
        return inflater.inflate(R.layout.auth_layout, container, false)
    }

    private fun firebaseAuth() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.AnonymousBuilder().build(),
        )

        val auth =
            AuthMethodPickerLayout.Builder(R.layout.auth_layout).setEmailButtonId(R.id.favorite_button)
                .setGoogleButtonId(R.id.add_button).setAnonymousButtonId(R.id.guest_button)
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

    private fun observeAuthenticatoinState() {
        val factToDisplay = viewModel.authenticationState.observe(viewLifecycleOwner, Observer {
            when (it) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                    Toast.makeText(
                        context,
                        "Congrats you are logged in ${it.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else ->
                    firebaseAuth()
            }
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_REQUEST_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
// User successfully signed in.
            } else {
// Sign in failed. If response is null, the user canceled the sign-in flow using the back button. Otherwise, check the error code and handle the error.
            }
        }
    }

}