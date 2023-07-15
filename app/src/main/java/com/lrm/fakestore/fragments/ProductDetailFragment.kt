package com.lrm.fakestore.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lrm.fakestore.R
import com.lrm.fakestore.adapter.ImageAdapter
import com.lrm.fakestore.databinding.FragmentProductDetailBinding
import com.lrm.fakestore.model.Product
import com.lrm.fakestore.viewModel.ProductViewModel

class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var product: Product

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener { findNavController().navigateUp() }

        viewModel.product.observe(viewLifecycleOwner) { selectedProduct ->
            product = selectedProduct
            Log.i("MyLogMessages", "onCreate: $product")
            bind(product)
            recyclerView = binding.imageRv
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = ImageAdapter(requireContext(), product.images)
        }
    }

    private fun bind(product: Product) {
        binding.apply {
            productName.text = product.title
            productBrand.text = product.brand
            productDescription.text = product.description
            productPrice.text = getString(R.string.price, product.price)
            productDiscount.text = "-${product.discountPercentage}%"
            productRating.text = getString(R.string.rating, product.rating)
            productStock.text = getString(R.string.stock, product.stock)
            productCategory.text = product.category
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}