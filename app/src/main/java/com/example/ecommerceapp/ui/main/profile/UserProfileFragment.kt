package com.example.ecommerceapp.ui.main.profile

import android.app.AlertDialog
import android.content.DialogInterface
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
    ): View?{

        val bind = FragmentUserProfileBinding.inflate(inflater)
        bind.lifecycleOwner = this
        bind.viewModel = viewModel
        val name = sharedPreferences.getString(PreferenceKeys.PREFERENCE_NAME_KEY, "NAME")!!
        val email = sharedPreferences.getString(PreferenceKeys.PREFERENCE_EMAIL_KEY, "Email")!!
        bind.fullname.text = name
        bind.nameEditText.setText(name)
        bind.emailEditText.setText(email)

        bind.logoutButton.setOnClickListener {
            val builder = AlertDialog.Builder(context, R.style.AlertDialogTheme);
            //titles
            builder.setTitle("Confirm Logout")
            builder.setMessage("Are you sure you want log out?")
            //buttons
            builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                viewModel.onNavigateToLauncherFragment()
                findNavController().navigate(R.id.action_userProfileFragment_to_navigation)
            })
            builder.setNegativeButton("No", null)
            builder.show()
        }

        bind.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return bind.root
    }

}