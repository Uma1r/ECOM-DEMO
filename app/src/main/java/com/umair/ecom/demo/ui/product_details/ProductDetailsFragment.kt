package com.umair.ecom.demo.ui.product_details


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.postDelayed
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.umair.ecom.demo.R
import com.umair.ecom.demo.data.remote.responses.ProductItemResponse
import com.umair.ecom.demo.databinding.FragmentProductDetailsBinding
import com.umair.ecom.demo.utils.AppConstants
import com.umair.ecom.demo.utils.gone
import com.umair.ecom.demo.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding
    private val viewModel: ProductDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productModel =
            arguments?.getParcelable<ProductItemResponse>(AppConstants.ParcelKeys.PRODUCT_ITEM)

        if (productModel != null) {
            setupViews()
            initObservations()

            viewModel.initAnimation(productModel)

        } else {
            findNavController().popBackStack()
        }
    }

    private fun initObservations() {
        viewModel.product.observe(viewLifecycleOwner) { product ->
            if (product != null) {
                binding.apply {
                    tvProductTitle.text = product.title
                    tvProductDescription.text = product.description
                    cpProductCategory.text = product.category
                    tvProductPrice.apply {
                        text = context.getString(
                            R.string.label_product_price,
                            product.price.toString()
                        )
                    }

                    ivProductImage.load(product.image) {
                        crossfade(true)
                        placeholder(R.color.loading_gray)
                    }
                }
                Handler(Looper.getMainLooper()).postDelayed(2000) {
                    viewModel.showContent()
                }
            } else {
                viewModel.showError(getString(R.string.retry))
            }
        }

        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                Loading -> {
                    binding.layoutShimmerLoading.root.show()
                    binding.layoutRetry.root.gone()
                    binding.llProductContainer.gone()
                }

                ContentState -> {
                    binding.layoutShimmerLoading.root.gone()
                    binding.layoutRetry.root.gone()
                    binding.llProductContainer.show()
                }

                EmptyState -> {
                    binding.layoutShimmerLoading.root.gone()
                    binding.layoutRetry.root.show()
                    binding.layoutRetry.txtHeading.text = getString(R.string.no_products_str)
                    binding.layoutRetry.txtSubHeading.gone()
                    binding.llProductContainer.gone()
                }

                is Error -> {
                    binding.layoutShimmerLoading.root.gone()
                    binding.layoutRetry.root.show()
                    binding.layoutRetry.txtSubHeading.text = uiState.message
                    binding.layoutRetry.txtSubHeading.show()
                    binding.llProductContainer.gone()
                }
            }
        }


    }

    private fun setupViews() {


        // Retry
        binding.layoutRetry.btnRetry.setOnClickListener {
            viewModel.product.value?.let { item ->
                viewModel.loadProductDetails(item.id)
            }
        }

        // Refresh
        binding.ivRefresh.setOnClickListener {
            viewModel.product.value?.let { item ->
                viewModel.loadProductDetails(item.id)
            }
        }
    }
}