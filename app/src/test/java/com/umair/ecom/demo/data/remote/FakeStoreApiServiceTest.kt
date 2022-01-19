package com.umair.ecom.demo.data.remote

import com.umair.ecom.demo.data.remote.responses.ProductItemResponse
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException

@RunWith(JUnit4::class)
class FakeStoreApiServiceTest : ApiBaseTest<FakeStoreApiService>() {

    private lateinit var apiService: FakeStoreApiService

    @Before
    fun setUp() {
        apiService = createService(FakeStoreApiService::class.java)
    }

    @After
    fun tearDown() {
    }

    @Throws(IOException::class)
    @Test
    fun `test loadProducts() returns list of Products`() = runBlocking {
        // Given
        enqueueResponse("/products_response.json")

        // Invoke
        val response = apiService.loadProducts()
        val responseBody = requireNotNull((response as List<ProductItemResponse>))
        mockWebServer.takeRequest()

        val productsList = responseBody

        // Then
        MatcherAssert.assertThat(productsList, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(
            productsList[0].title,
            `is`("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops")
        )
        MatcherAssert.assertThat(
            productsList[0].image,
            `is`("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg")
        )
        MatcherAssert.assertThat(
            productsList[0].category,
            `is`("men's clothing")
        )
    }
}