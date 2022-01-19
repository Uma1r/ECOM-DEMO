package com.umair.ecom.demo.data.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.umair.ecom.demo.TestingCoroutineRule
import com.umair.ecom.demo.data.DataResource
import com.umair.ecom.demo.data.repository.ProductsRepository
import com.umair.ecom.demo.utils.TestDataUtils
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FetchAllProductsUseCaseTest {

    @MockK
    private lateinit var repository: ProductsRepository

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = TestingCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test invoking FetchAllProductsUseCase gives list of Products`() = runBlocking {
        // Given
        val usecase = FetchAllProductsUseCase(repository, coroutinesRule.testDispatcher)
        val givenProducts = TestDataUtils.createProductItemsList(3)

        // When
        coEvery { repository.fetchAllProducts() }
            .returns(flowOf(DataResource.Success(givenProducts)))

        // Invoke
        val productsListFlow = usecase(Unit)

        // Then
        MatcherAssert.assertThat(productsListFlow, CoreMatchers.notNullValue())

        val productsListDataResource = productsListFlow.last()
        MatcherAssert.assertThat(productsListDataResource, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(
            productsListDataResource,
            CoreMatchers.instanceOf(DataResource.Success::class.java)
        )

        val productsList = (productsListDataResource as DataResource.Success).data
        MatcherAssert.assertThat(productsList, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(productsList.size, `is`(givenProducts.size))
    }
}