package com.example.ecommerceapp.ui.auth.login

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
import com.example.ecommerceapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var bind: FragmentLoginBinding
    val viewModel: LoginFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentLoginBinding.inflate(inflater)
        bind.lifecycleOwner = this
        bind.viewModel = viewModel

        viewModel.navigateToRegisterPage.observe(viewLifecycleOwner, Observer {

            val email = bind.emailEditText.text.toString().trim()
            val password = bind.passwordEditText.text.toString().trim()
            isEmailValid(email)
            isPasswordValid(password)
            if (checkIfErrorFree()) {
                viewModel.loginToServer(email, password)
            }
        })

        viewModel.response.observe(viewLifecycleOwner, Observer {
            if (!it.error!!) {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment2_to_homeFragment)
            } else {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }

        })

        bind.registerText.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_registerFragment2)
        }

        bind.backButton.setOnClickListener {
            findNavController().popBackStack()
        }



        return bind.root
    }

    private fun checkIfErrorFree(): Boolean {
        return bind.emailTextInput.error.isNullOrEmpty() && bind.passwordTextInput.error.isNullOrEmpty()
    }

    private fun isPasswordValid(password: String?) {
        bind.passwordTextInput.error = when {
            password.isNullOrEmpty() -> "Enter password"
            password.length < 8 -> "Enter a valid password"
            else -> null
        }
    }

    private fun isEmailValid(email: String?) {
        bind.emailTextInput.error = when {
            email.isNullOrEmpty() -> "Enter email address"
            email.length < 8 -> "Enter a valid email address"
            else -> null
        }
    }

}