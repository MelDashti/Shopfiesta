package com.example.ecommerceapp.ui.main.profile

import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

    private val viewModel: UserProfileViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentUserProfileBinding.inflate(inflater)
        binding.viewModel=viewModel
        binding.lifecycleOwner=this.viewLifecycleOwner
        val name = sharedPreferences.getString(PreferenceKeys.PREFERENCE_NAME_KEY, "NAME")!!
        val email = sharedPreferences.getString(PreferenceKeys.PREFERENCE_EMAIL_KEY, "Email")!!
        binding.fullname.text = name
        binding.nameEditText.setText(name)
        binding.emailEditText.setText(email)

        binding.logoutButton.setOnClickListener {
            showAlertDialog(it)
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    private fun showAlertDialog(it: View?) {
        val builder = AlertDialog.Builder(context, R.style.AlertDialogTheme)
        //titles
        builder.setTitle("Confirm Logout")
        builder.setMessage("Are you sure you want log out?")
        //buttons
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.onNavigateToLauncherFragment()
            findNavController().navigate(R.id.action_userProfileFragment_to_navigation)
        }
        builder.setNegativeButton("No", null)
        builder.show()
    }

}