package com.example.ecommerceapp.ui.main.home

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapter.*
import com.example.ecommerceapp.databinding.FragmentHomeBinding
import com.example.ecommerceapp.domain.Category
import com.example.ecommerceapp.domain.Group
import com.example.ecommerceapp.util.FilterType
import com.example.ecommerceapp.util.PreferenceKeys
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater)

        binding.viewModel=viewModel
        binding.lifecycleOwner=this.viewLifecycleOwner

        binding.toolbarRef.userName.text =
            sharedPreferences.getString(PreferenceKeys.PREFERENCE_NAME_KEY, "Guest")

        //popular list recycler view initialization


        val popularListAdapter = initializeProductAdapter()

        val recentlyViewedAdapter = initializeProductAdapter()

        // Category recycler view initialization
        val categoryListAdapter = CategoryItemAdapter(CategoryListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToListFragment(it))
        })

        categoryListAdapter.data = createCategoryData()

        //instead of this tiresome process this can be done directly in the xml file using binding adapters
        viewModel.listResult.observe(viewLifecycleOwner, {
            popularListAdapter.submitList(viewModel.applyFiltering(FilterType.POPULAR))
            recentlyViewedAdapter.submitList(viewModel.applyFiltering(FilterType.RECENTLY_VIEWED))
        })

        val groupAdapter =
            initializeGroupAdapter(popularListAdapter, recentlyViewedAdapter, categoryListAdapter)


        groupAdapter.submitList(
            listOf(
                Group(title = "Category", filterType = FilterType.CATEGORY),
                Group(title = "Recently Viewed", filterType = FilterType.RECENTLY_VIEWED),
                Group(title = "Popular", filterType = FilterType.POPULAR),
                Group(title = "Trending", filterType = FilterType.TRENDING),
                Group(title = "Great", filterType = FilterType.GREAT)
            )
        )

        binding.groupRecyclerView.adapter = groupAdapter

        addDecorationToGroupRecyclerView()

        binding.fab.setOnClickListener {
            navigateToCart()
        }

        viewModel.navigateToUserProfile.observe(viewLifecycleOwner, {
            navigateToUserProfile(it)
        })

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            NavigationUI.onNavDestinationSelected(it, findNavController()) || onOptionsItemSelected(
                it
            )
        }

        // set your custom toolbar as support action bar
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarRef.toolbar)
        // show overflow menu
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun navigateToUserProfile(it: Boolean) {
        if (it) {
            if (viewModel.checkIfAuthenticated()) {
                findNavController().navigate(R.id.action_homeFragment_to_userProfileFragment)
                viewModel.navigatedToUserProfile()
            } else findNavController().navigate(R.id.action_homeFragment_to_navigation)
        }
    }


    private fun addDecorationToGroupRecyclerView() {
        val dividerItemDecoration = DividerItemDecoration(
            context,
            DividerItemDecoration.VERTICAL
        )
        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!
        )

        binding.groupRecyclerView.addItemDecoration(dividerItemDecoration)
    }


    private fun initializeGroupAdapter(
        popularListAdapter: ProductAdapter,
        recentlyViewedAdapter: ProductAdapter,
        categoryListAdapter: CategoryItemAdapter
    ): GroupAdapter {
        return GroupAdapter(GroupItemListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToListFragment(
                    it
                )
            )
        }, popularListAdapter, recentlyViewedAdapter, categoryListAdapter)
    }


    private fun createCategoryData(): List<Category> {
        return listOf(
            Category(
                name = "Phones",
                drawable = R.drawable.ic_phones,
                filterType = FilterType.PHONES
            ),
            Category(
                name = "Laptops",
                drawable = R.drawable.ic_laptops,
                filterType = FilterType.LAPTOPS
            ),
            Category(
                name = "Accessories",
                drawable = R.drawable.ic_accessories,
                filterType = FilterType.ACCESSORIES
            )
        )
    }


    private fun initializeProductAdapter(): ProductAdapter {
        return ProductAdapter(ProductListener { productID: String, view: View ->
            val extraInfoForSharedElement =
                FragmentNavigatorExtras(view to productID)
            // this is the same as writing pair(imageView, imageView.transitionName)
            val action =
                HomeFragmentDirections.actionHomeFragmentToProductInfoFragment(productID)
            findNavController().navigate(action, extraInfoForSharedElement)
            // now we have to let the destination fragment know that there's an element to transition.
            // We can define the kind of transition we want.
        })
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


}
