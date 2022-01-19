package com.umair.ecom.demo.data.usecases

import com.umair.ecom.demo.data.DataResource
import com.umair.ecom.demo.data.remote.responses.ProductItemResponse
import com.umair.ecom.demo.data.repository.ProductsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

class FetchAllProductsUseCase(
    private val productsRepository: ProductsRepository
) {
    @ExperimentalCoroutinesApi
    operator fun invoke(): Flow<DataResource<List<ProductItemResponse>>> =
        productsRepository.fetchAllProducts()
}