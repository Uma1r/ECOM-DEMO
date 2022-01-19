package com.umair.ecom.demo.data.repository

import com.umair.ecom.demo.data.DataResource
import com.umair.ecom.demo.data.callApi
import com.umair.ecom.demo.data.remote.responses.ProductItemResponse
import com.umair.ecom.demo.data.remote.FakeStoreApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

typealias ProductsListResponse = DataResource<List<ProductItemResponse>>
typealias ProductResponse = DataResource<ProductItemResponse>

interface ProductsRepository {
    fun fetchAllProducts(): Flow<ProductsListResponse>
    fun fetchProductDetails(id: Int): Flow<ProductResponse>
}

class ProductsRepositoryImpl @Inject constructor(
    private val apiService: FakeStoreApiService
) : ProductsRepository {

    override fun fetchAllProducts() = flow {
        emit(DataResource.Loading)
        val result = callApi(apiService::loadProducts)
        emit(result)
    }.flowOn(Dispatchers.IO)

    override fun fetchProductDetails(id: Int) =
        flow {
            emit(DataResource.Loading)
            val result = callApi { apiService.loadProductDetails(id = id) }
            emit(result)
        }.flowOn(Dispatchers.IO)
}