package com.umair.ecom.demo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umair.ecom.demo.databinding.FragmentProductListingBinding

class ProductListingFragment : Fragment() {

    private lateinit var binding: FragmentProductListingBinding

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
    }

    private fun initObservations() {
        //TODO("Not yet implemented")
    }

    private fun setupViews() {
        //TODO("Not yet implemented")
    }
}