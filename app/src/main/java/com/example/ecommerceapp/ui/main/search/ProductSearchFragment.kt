package com.example.ecommerceapp.ui.main.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ecommerceapp.adapter.SearchItemAdapter
import com.example.ecommerceapp.adapter.SearchItemListener
import com.example.ecommerceapp.databinding.FragmentProductSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductSearchFragment : Fragment() {
    val viewModel: ProductSearchViewModel by viewModels()
    lateinit var binding: FragmentProductSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProductSearchBinding.inflate(inflater)
        binding.viewModel=viewModel
        binding.lifecycleOwner=this.viewLifecycleOwner
        val searchItemAdapter = SearchItemAdapter(SearchItemListener {
            findNavController().navigate(
                ProductSearchFragmentDirections.actionProductSearchFragmentToProductInfoFragment(
                    it
                )
            )
        })

        binding.productList.adapter = searchItemAdapter

        viewModel.searchResultList.observe(viewLifecycleOwner, {
            searchItemAdapter.submitList(it)
        })

        viewModel.startSearch.observe(viewLifecycleOwner, {
            initializeSearch()
        })

        return binding.root
    }


    private fun initializeSearch() {
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = binding.searchView
        searching(searchView)
    }

    private fun searching(search: SearchView) {
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchNow(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        }


        )


    }


}








