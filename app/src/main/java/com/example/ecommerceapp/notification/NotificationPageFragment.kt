package com.example.ecommerceapp.notification

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapter.NotificationAdapter
import com.example.ecommerceapp.databinding.FragmentNotificationPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationPageFragment : Fragment() {

    private val viewModel: NotificationViewModel by viewModels()
    lateinit var binding: FragmentNotificationPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationPageBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        val adapter = NotificationAdapter()
        binding.notificationList.adapter = adapter

        viewModel.notificationList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        binding.clearListText.setOnClickListener {
            val builder = AlertDialog.Builder(context, R.style.AlertDialogTheme)
            //titles
            builder.setTitle("Confirm Action")
            builder.setMessage("Are you sure you want to permanently remove these items? \n ")
            //buttons
            builder.setPositiveButton("Yes") { _, _ ->
                viewModel.clearNotificationList()
            }
            builder.setNegativeButton("No", null)
            builder.show()
        }



        return binding.root
    }
}