package com.example.ecommerceapp.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ecommerceapp.adapters.ProductAdapter
import com.example.ecommerceapp.databinding.FragmentHomeBinding
import com.example.ecommerceapp.network.Product


class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = FragmentHomeBinding.inflate(inflater)
        val adapter = ProductAdapter()
        val list = listOf(
            Product("df", "dsf", "dsf"),
            Product("df", "dsf", "dsf"),
            Product("df", "dsf", "dsf"),
            Product("df", "dsf", "dsf"),
            Product("df", "dsf", "dsf"),
            Product("df", "dsf", "dsf"),
            Product("df", "dsf", "dsf"),
            Product("df", "dsf", "dsf")
        )
        adapter.submitList(list)
        bind.categoryRecyclerView.adapter = adapter
        bind.popularRecyclerView.adapter = adapter
        bind.recentlyViewedRecyclerView.adapter = adapter


        return bind.root
    }

}