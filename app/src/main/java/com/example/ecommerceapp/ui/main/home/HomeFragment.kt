package com.example.ecommerceapp.ui.main.home

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentHomeBinding
import com.example.ecommerceapp.domain.Category
import com.example.ecommerceapp.domain.Group
import com.example.ecommerceapp.home.*
import com.example.ecommerceapp.util.FilterType
import com.example.ecommerceapp.util.PreferenceKeys
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bind = FragmentHomeBinding.inflate(inflater)

        bind.lifecycleOwner = this
        bind.viewModel = viewModel

        bind.toolbarRef.userName.text =
            sharedPreferences.getString(PreferenceKeys.PREFERENCE_NAME_KEY, "Guest")
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

        //instead of this tiresome process this can be done directly in the xml file using binding adapters
        viewModel.listResult.observe(viewLifecycleOwner, Observer {
            popularListAdapter.submitList(viewModel.applyFiltering(FilterType.POPULAR))
            recentlyViewedAdapter.submitList(viewModel.applyFiltering(FilterType.RECENTLY_VIEWED))
        })

        val groupAdapter =
            GroupAdapter(popularListAdapter, recentlyViewedAdapter, categoryListAdapter)


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

        bind.fab.setOnClickListener {
            navigateToCart()
        }
        viewModel.navigateToUserProfile.observe(viewLifecycleOwner, {
            if (it) {
                findNavController().navigate(R.id.action_homeFragment_to_userProfileFragment)
                viewModel.navigatedToUserProfile()
            }
        })

        // set your custom toolbar as support action bar
        (activity as AppCompatActivity).setSupportActionBar(bind.toolbarRef.toolbar)
        // show overflow menu
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        setHasOptionsMenu(true)

        return bind.root
    }

    private fun navigateToCart() {
        findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        )
                || super.onOptionsItemSelected(item)
    }

    fun initializeGroupList() {
    }


}