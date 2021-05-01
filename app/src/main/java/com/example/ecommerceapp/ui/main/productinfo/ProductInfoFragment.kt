package com.example.ecommerceapp.ui.main.productinfo

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentProductInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductInfoFragment : Fragment() {
    private val viewModel: ProductInfoViewModel by viewModels()
    lateinit var binding: FragmentProductInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductInfoBinding.inflate(inflater)
        val productId = ProductInfoFragmentArgs.fromBundle(requireArguments()).product
        // Now with the above method used the animation is not ruined because the image is yet to be loaded.
        binding.viewModel = viewModel
        Log.d("hello1",productId)
        binding.imageView4.transitionName = productId
        viewModel.fetchProductInfo(productId)
        binding.lifecycleOwner = this.viewLifecycleOwner
          val transition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition=transition
        sharedElementReturnTransition=transition

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.cartButton.setOnClickListener {
            findNavController().navigate(R.id.action_productInfoFragment_to_cartFragment)
        }

        return binding.root
    }

}