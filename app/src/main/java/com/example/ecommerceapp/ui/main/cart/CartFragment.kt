package com.example.ecommerceapp.ui.main.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapter.CartItemAdapter
import com.example.ecommerceapp.adapter.CartItemListener
import com.example.ecommerceapp.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    val viewModel: CartFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCartBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = CartItemAdapter(CartItemListener {
            findNavController().navigate(
                CartFragmentDirections.actionCartFragmentToProductInfoFragment(
                    it
                )
            )
        })
        binding.cartList.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(
            context,
            DividerItemDecoration.VERTICAL
        )
        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!
        )
        binding.cartList.addItemDecoration(dividerItemDecoration)

        viewModel.fetchCartItem()
        viewModel.product.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            binding.cartList.scheduleLayoutAnimation()
        })
        binding.cartList.adapter
        return binding.root

    }

}