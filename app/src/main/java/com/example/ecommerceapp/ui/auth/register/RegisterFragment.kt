package com.example.ecommerceapp.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            if (it) {
                if (fullname == null)
                    bind.fullnameEditText.error = "Please Enter Your Full Name"
                else if (email == null)
                    bind.emailEditText.error = "Please Enter Your Email"
                else if (password == null)
                    bind.passwordTextInput.error = "Enter A Password"
                else {
                    viewModel.registerOnWebServer(fullname, email, password)
                    findNavController().navigate(R.id.action_registerFragment2_to_loginFragment2)
                }
            }
        })
        return bind.root
    }

    private fun checkIfEmpty() {

    }

}