package com.example.ecommerceapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentHomeBinding
import com.example.ecommerceapp.domain.Category
import com.example.ecommerceapp.domain.Group
import com.example.ecommerceapp.util.FilterType
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
        //popular list recycler view initialization
        val popularListAdapter = ProductAdapter(ProductListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToProductInfoFragment(
                    it
                )
            )
        })
        val recentlyViewedAdapter = ProductAdapter(ProductListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToProductInfoFragment(
                    it
                )
            )
        })
        // Category recycler view initialization
        val categoryListAdapter = CategoryItemAdapter(CategoryListener {
            findNavController().navigate(R.id.action_homeFragment_to_itemFragment)
        })

        categoryListAdapter.data =
            listOf(
                Category(name = "Phones", drawable = R.drawable.ic_phones),
                Category(name = "Phones", drawable = R.drawable.ic_laptops),
                Category(name = "Accessories", drawable = R.drawable.ic_accessories)
            )
        bind.lifecycleOwner = this

        //instead of this tiresome process this can be done directly in the xml file using binding adapters
        viewModel.listResult.observe(viewLifecycleOwner, Observer {
            popularListAdapter.submitList(viewModel.applyFiltering(FilterType.POPULAR))
            recentlyViewedAdapter.submitList(viewModel.applyFiltering(FilterType.RECENTLY_VIEWED))
        })

        val groupAdapter =
            GroupAdapter(popularListAdapter,recentlyViewedAdapter,categoryListAdapter)


        groupAdapter.submitList(
            listOf(
                Group(title = "Category"), Group(title = "Recently Viewed"),
                Group(title = "Popular"),
                Group(title = "Trending"),
                Group(title = "Great")
            )
        )

        bind.groupRecyclerView.adapter = groupAdapter

        val dividerItemDecoration = DividerItemDecoration(
            context,
            DividerItemDecoration.VERTICAL
        )
        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!
        )

        bind.groupRecyclerView.addItemDecoration(dividerItemDecoration)

        (activity as AppCompatActivity).setSupportActionBar(bind.toolbarRef.toolbar)
        return bind.root
    }

    fun initializeGroupList() {
    }


}