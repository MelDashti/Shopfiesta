package com.example.ecommerceapp.ui.main.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ecommerceapp.adapter.FavoriteItemAdapter
import com.example.ecommerceapp.adapter.FavoriteItemListener
import com.example.ecommerceapp.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteFragmentViewModel by viewModels()
    private lateinit var bind: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        bind = FragmentFavoriteBinding.inflate(inflater)
        bind.lifecycleOwner = this
        bind.viewModel = viewModel

        bind.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        val adapter = FavoriteItemAdapter(FavoriteItemListener {
            findNavController().navigate(
                FavoriteFragmentDirections.actionFavoriteFragmentToProductInfoFragment(
                    it
                )
            )
        })
        viewModel.list.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
        bind.favoriteProductList.adapter = adapter


        return bind.root


    }

}