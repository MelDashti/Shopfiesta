package com.example.ecommerceapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.ecommerceapp.adapters.ProductAdapter
import com.example.ecommerceapp.databinding.FragmentHomeBinding
import com.example.ecommerceapp.network.EcomProduct
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bind = FragmentHomeBinding.inflate(inflater)
        val adapter = ProductAdapter()
        bind.setLifecycleOwner(this)

        viewModel.response.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
        viewModel.listResult.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        bind.categoryRecyclerView.adapter = adapter
        bind.popularRecyclerView.adapter = adapter
        bind.recentlyViewedRecyclerView.adapter = adapter


        return bind.root
    }

}