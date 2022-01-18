package com.umair.ecom.demo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.umair.ecom.demo.R
import com.umair.ecom.demo.adapters.ProductAdapter
import com.umair.ecom.demo.databinding.FragmentProductListingBinding
import com.umair.ecom.demo.utils.gone
import com.umair.ecom.demo.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListingFragment : Fragment() {

    private lateinit var binding: FragmentProductListingBinding
    private val viewModel: ProductListingViewModel by viewModels()
    private var recyclerAdapter: ProductAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        initObservations()

        viewModel.loadProducts()
    }

    private fun initObservations() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                Loading -> {
                    binding.layoutShimmerLoading.root.show()
                    binding.layoutRetry.root.gone()
                    binding.rvProducts.gone()
                }

                ContentState -> {
                    binding.layoutShimmerLoading.root.gone()
                    binding.layoutRetry.root.gone()
                    binding.rvProducts.show()
                }

                EmptyState -> {
                    binding.layoutShimmerLoading.root.gone()
                    binding.layoutRetry.root.show()
                    binding.layoutRetry.txtHeading.text = getString(R.string.no_products_str)
                    binding.layoutRetry.txtSubHeading.gone()
                    binding.rvProducts.gone()
                }

                is Error -> {
                    binding.layoutShimmerLoading.root.gone()
                    binding.layoutRetry.root.show()
                    binding.layoutRetry.txtSubHeading.text = uiState.message
                    binding.layoutRetry.txtSubHeading.show()
                    binding.rvProducts.gone()
                }
            }
        }

        viewModel.productsList.observe(viewLifecycleOwner) { productsList ->
            recyclerAdapter?.replaceList(productsList)
        }
    }

    private fun setupViews() {
        // Recycler
        recyclerAdapter = ProductAdapter()
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = recyclerAdapter
        }

        // Retry
        binding.layoutRetry.btnRetry.setOnClickListener {
            viewModel.loadProducts()
        }
    }
}