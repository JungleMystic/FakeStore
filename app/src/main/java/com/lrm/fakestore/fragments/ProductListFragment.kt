package com.lrm.fakestore.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lrm.fakestore.adapter.ProductAdapter
import com.lrm.fakestore.databinding.FragmentProductListBinding
import com.lrm.fakestore.viewModel.ProductViewModel

class ProductListFragment : Fragment() {
    private val viewModel: ProductViewModel by activityViewModels()

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProductAdapter(requireContext()){
            viewModel.updateCurrentProduct(it)
            Log.i("MyLogMessages", "onItemClicked: $it")
            val action = ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment()
            findNavController().navigate(action)
        }

        binding.recyclerView.adapter = adapter
        viewModel.dataList.observe(viewLifecycleOwner) { list ->
            list.let { adapter.submitList(it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}