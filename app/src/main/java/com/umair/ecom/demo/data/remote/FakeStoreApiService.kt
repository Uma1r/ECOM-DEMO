package com.umair.ecom.demo.data.remote


import com.umair.ecom.demo.data.models.ProductItemResponse
import com.umair.ecom.demo.utils.AppConstants
import retrofit2.http.GET

interface FakeStoreApiService {

    @GET(AppConstants.APIEndpoints.GET_PRODUCT_LISTING)
    suspend fun loadProducts(): List<ProductItemResponse>
}