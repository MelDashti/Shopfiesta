package com.example.ecommerceapp.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            if (it) {
                viewModel.loginToServer(
                    bind.emailEditText.text.toString().trim(),
                    bind.passwordEditText.text.toString().trim()
                )
                findNavController().navigate(R.id.action_loginFragment2_to_homeFragment)

            }
        })



        return bind.root
    }

}