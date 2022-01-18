package com.umair.ecom.demo.data.repository

import com.umair.ecom.demo.data.DataResource
import com.umair.ecom.demo.data.callApi
import com.umair.ecom.demo.data.models.ProductItemResponse
import com.umair.ecom.demo.data.remote.FakeStoreApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface ProductsRepository {
    fun fetchAllProducts(): Flow<DataResource<List<ProductItemResponse>>>
}

class DefaultProductsRepository @Inject constructor(
    private val apiService: FakeStoreApiService,
    val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ProductsRepository {

    override fun fetchAllProducts(): Flow<DataResource<List<ProductItemResponse>>> = flow {
        emit(DataResource.Loading)
        val result = callApi {
            val productsResponse = apiService.loadProducts()
            productsResponse
        }
        emit(result)
    }.flowOn(dispatcher)
}