package com.umair.ecom.demo.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.umair.ecom.demo.TestingCoroutineRule
import com.umair.ecom.demo.data.DataResource
import com.umair.ecom.demo.data.remote.responses.ProductItemResponse
import com.umair.ecom.demo.data.usecases.FetchAllProductsUseCase
import com.umair.ecom.demo.utils.TestDataUtils
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProductListingViewModelTest {

    // Subject under Test
    private lateinit var viewModel: ProductListingViewModel

    @MockK
    private lateinit var loadAllProductsUseCase: FetchAllProductsUseCase

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = TestingCoroutineRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test when ProductListingViewModelTest's loadProducts is called, list of products are fetch`() =
        runBlocking {
            // Given
            val givenProducts = TestDataUtils.createProductItemsList(3)
            val uiObserver = mockk<Observer<ProductUIState>>(relaxed = true)
            val productsListObserver = mockk<Observer<List<ProductItemResponse>>>(relaxed = true)

            // When
            coEvery { loadAllProductsUseCase.invoke(Unit) }
                .returns(flowOf(DataResource.Success(givenProducts)))

            // Invoke
            viewModel = ProductListingViewModel(loadAllProductsUseCase)
            viewModel.loadProducts()
            viewModel.uiState.observeForever(uiObserver)
            viewModel.productsList.observeForever(productsListObserver)

            // Then
            coVerify(exactly = 1) { loadAllProductsUseCase.invoke(Unit) }
            verify { uiObserver.onChanged(match { it == ContentState }) }
            verify { productsListObserver.onChanged(match { it.size == givenProducts.size }) }
        }
}