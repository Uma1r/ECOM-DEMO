package com.umair.ecom.demo.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.umair.ecom.demo.TestingCoroutineRule
import com.umair.ecom.demo.data.DataResource
import com.umair.ecom.demo.data.remote.FakeStoreApiService
import com.umair.ecom.demo.utils.TestDataUtils
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DefaultProductsRepositoryTest {

    // Subject under Test
    private lateinit var repositoryImpl: ProductsRepositoryImpl

    @MockK
    private lateinit var apiService: FakeStoreApiService

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = TestingCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test fetchAllProducts() gives list of Products`() = runBlocking {
        // Given
        repositoryImpl = ProductsRepositoryImpl(apiService)
        val productsResponse = TestDataUtils.createProductItemsList(3)
        val givenProductsList = productsResponse

        // When
        coEvery { apiService.loadProducts() }
            .returns(givenProductsList)

        // Invoke
        val apiResponseFlow = repositoryImpl.fetchAllProducts()

        // Then
        MatcherAssert.assertThat(apiResponseFlow, CoreMatchers.notNullValue())

        val productsDataResource = apiResponseFlow.last()
        MatcherAssert.assertThat(productsDataResource, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(
            productsDataResource,
            CoreMatchers.instanceOf(DataResource.Success::class.java)
        )

        val productsList = (productsDataResource as DataResource.Success).data
        MatcherAssert.assertThat(productsList, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(productsList.size, CoreMatchers.`is`(givenProductsList.size))

        coVerify(exactly = 1) { apiService.loadProducts() }
        confirmVerified(apiService)
    }
}