package com.example.ecommerceapp.ui.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapter.ProductAdapter
import com.example.ecommerceapp.adapter.ProductListener
import com.example.ecommerceapp.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    private val viewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentListBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val filteredListAdapter =
            ProductAdapter(ProductListener { productId: String, imageView: ImageView ->
                val extraInfoForSharedElement =
                    FragmentNavigatorExtras(imageView to productId)
                val action =
                    ListFragmentDirections.actionListFragmentToProductInfoFragment(productId)
                findNavController().navigate(action, extraInfoForSharedElement)
            })

        val filter = ListFragmentArgs.fromBundle(requireArguments()).filter
        binding.headerText.text = filter.toString()
        filteredListAdapter.submitList(viewModel.applyFiltering(filter))


        viewModel.listResult.observe(viewLifecycleOwner, Observer {
            //whenever the list changes on the server, this callback is triggered and the list is filtered again based on the new data.
            filteredListAdapter.submitList(viewModel.applyFiltering(filter))
        })

        binding.list.layoutManager = GridLayoutManager(context, 2)
        binding.list.adapter = filteredListAdapter
        val dividerItemDecoration = DividerItemDecoration(
            context,
            DividerItemDecoration.VERTICAL
        )
        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!
        )

        binding.list.addItemDecoration(dividerItemDecoration)

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }


        return binding.root
    }
}