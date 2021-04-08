package com.example.ecommerceapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentLauncherBinding

class LauncherFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLauncherBinding.inflate(inflater)
        binding.signInButton.setOnClickListener {
            findNavController().navigate(R.id.action_launcherFragment_to_loginFragment2)
        }

        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_launcherFragment_to_registerFragment2)
        }

        binding.guestButton.setOnClickListener{
            findNavController().navigate(R.id.action_launcherFragment_to_homeFragment)
        }
        return binding.root
    }


}