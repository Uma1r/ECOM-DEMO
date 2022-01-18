package com.umair.ecom.demo.data.usecases

import com.umair.ecom.demo.data.DataResource
import com.umair.ecom.demo.data.models.ProductItemResponse
import com.umair.ecom.demo.data.repository.ProductsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

class FetchAllProductsUseCase(
    private val productsRepository: ProductsRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
): FlowUseCase<Unit, DataResource<List<ProductItemResponse>>>(dispatcher) {

    @ExperimentalCoroutinesApi
    override fun execute(parameters: Unit): Flow<DataResource<List<ProductItemResponse>>> = productsRepository.fetchAllProducts()
}