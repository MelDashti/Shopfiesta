package com.example.ecommerceapp.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.ecommerceapp.adapters.SearchItemAdapter
import com.example.ecommerceapp.adapters.SearchItemListener
import com.example.ecommerceapp.databinding.FragmentProductSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductSearchFragment : Fragment() {
    public val viewModel: ProductSearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bind = FragmentProductSearchBinding.inflate(inflater)

        bind.viewModel = viewModel
        bind.lifecycleOwner = this

        val searchItemAdapter = SearchItemAdapter(SearchItemListener {
            findNavController().navigate(
                ProductSearchFragmentDirections.actionProductSearchFragmentToProductInfoFragment(
                    it
                )
            )
        })

        bind.productList.adapter = searchItemAdapter
        viewModel.searchList.observe(viewLifecycleOwner, Observer {
            searchItemAdapter.submitList(it)

        })


        return bind.root
    }

}