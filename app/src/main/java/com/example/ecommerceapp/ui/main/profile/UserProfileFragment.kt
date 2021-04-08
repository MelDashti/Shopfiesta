package com.example.ecommerceapp.ui.main.profile

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentUserProfileBinding
import com.example.ecommerceapp.util.PreferenceKeys
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserProfileFragment : Fragment() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

     private val viewModel : UserProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bind = FragmentUserProfileBinding.inflate(inflater)
        bind.lifecycleOwner = this
        bind.viewModel = viewModel
        val name = sharedPreferences.getString(PreferenceKeys.PREFERENCE_NAME_KEY, "NAME")!!
        val email = sharedPreferences.getString(PreferenceKeys.PREFERENCE_EMAIL_KEY, "Email")!!
        bind.fullname.text = name
        bind.nameEditText.setText(name)
        bind.emailEditText.setText(email)

        viewModel.navigateToLauncherFragment.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_userProfileFragment_to_navigation)
        })

        return bind.root
    }

}