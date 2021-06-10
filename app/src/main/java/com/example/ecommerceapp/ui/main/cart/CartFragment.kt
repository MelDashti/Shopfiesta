package com.example.ecommerceapp.ui.main.cart

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapter.CartItemAdapter
import com.example.ecommerceapp.adapter.CartItemListener
import com.example.ecommerceapp.api.main.responses.CartProduct
import com.example.ecommerceapp.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class CartFragment : Fragment() {

    val viewModel: CartFragmentViewModel by viewModels()
    lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        val adapter = CartItemAdapter(CartItemListener {
            findNavController().navigate(
                CartFragmentDirections.actionCartFragmentToProductInfoFragment(
                    it
                )
            )
        }, viewModel)


        binding.cartList.adapter = adapter
        //check if you can add decoration in the adapter class
        addDecorationsToAdapter()

//        viewModel.fetchCartItem()

        viewModel.cartProducts.observe(viewLifecycleOwner, {
            calculateTotalAmount(it)
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })

        binding.checkOutButton.setOnClickListener {
            showAlertDialog(it)
        }
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root

    }

    private fun showAlertDialog(it: View?) {
        val builder = AlertDialog.Builder(context, R.style.AlertDialogTheme)
        //titles
        builder.setTitle("Confirm Order")
        builder.setMessage("Are you sure you place this order? \n ")
        //buttons
        builder.setPositiveButton("Yes") { _, _ ->
        }
        builder.setNegativeButton("No", null)
        builder.show()
    }

    private fun addDecorationsToAdapter() {
        val dividerItemDecoration = DividerItemDecoration(
            context,
            DividerItemDecoration.VERTICAL
        )
        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!
        )
        binding.cartList.addItemDecoration(dividerItemDecoration)
    }

    private fun calculateTotalAmount(list: List<CartProduct>) {
        var totalAmount = 0.00
        list.forEach {
            totalAmount += (it.price * it.quantity)
        }
        binding.totalAmount.text = "Total Amount: ${totalAmount.roundToInt().toString() + "$"}"
    }

}