package com.umair.ecom.demo.data.remote


import com.umair.ecom.demo.data.remote.responses.ProductItemResponse
import com.umair.ecom.demo.utils.AppConstants
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeStoreApiService {

    @GET(AppConstants.APIEndpoints.GET_PRODUCT_LISTING)
    suspend fun loadProducts(): List<ProductItemResponse>

    @GET(AppConstants.APIEndpoints.GET_PRODUCT_DETAILS)
    suspend fun loadProductDetails(@Path("id") id: Int): ProductItemResponse
}