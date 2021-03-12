package com.example.ecommerceapp.ui.main.productinfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
        binding.viewModel = viewModel
        viewModel.fetchProductInfo(productId)
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }
}