package com.example.ecommerceapp.ui.auth.register

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
import com.example.ecommerceapp.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    lateinit var bind: FragmentRegisterBinding
    val viewModel: RegisterFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentRegisterBinding.inflate(inflater)
        bind.lifecycleOwner = this
        bind.viewModel = viewModel

        viewModel.navigateToLoginPage.observe(viewLifecycleOwner, Observer {
            val email = bind.emailEditText.text.toString().trim()
            val fullname = bind.fullnameEditText.text.toString().trim()
            val password = bind.passwordEditText.text.toString().trim()

            isFullNameValid(fullname)
            isEmailValid(email)
            isPasswordValid(password)
            if (checkIfErrorFree()) {
                viewModel.registerOnWebServer(fullname, email, password)
            }
        })

        viewModel.response.observe(viewLifecycleOwner, Observer {

            if (!it.error!!) {
                findNavController().navigate(R.id.action_registerFragment2_to_loginFragment2)
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }

        })



        return bind.root
    }

    private fun checkIfErrorFree(): Boolean {
        return bind.fullnameTextInput.error.isNullOrEmpty() && bind.emailTextInput.error.isNullOrEmpty() && bind.passwordTextInput.error.isNullOrEmpty()
    }

    private fun isFullNameValid(text: String?) {
        bind.fullnameTextInput.error = when {
            text.isNullOrEmpty() -> "Enter full name"
            else -> null
        }
    }

    private fun isEmailValid(text: String?) {
        bind.emailTextInput.error = when {
            text.isNullOrEmpty() -> "Enter email address"
            text.length < 8 -> {
                "Enter a valid email address"
            }
            else -> null
        }
    }

    private fun isPasswordValid(text: String?) {
        bind.passwordTextInput.error = when {
            text.isNullOrEmpty() -> "Enter password"
            text.length < 8 -> {
                "Use 8 characters or more for your password"
            }
            else -> null
        }
    }


}