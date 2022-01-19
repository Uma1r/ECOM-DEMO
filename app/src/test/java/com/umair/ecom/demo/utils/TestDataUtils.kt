package com.umair.ecom.demo.utils

import com.umair.ecom.demo.data.remote.responses.ProductItemResponse
import com.umair.ecom.demo.data.remote.responses.Rating

object TestDataUtils {

    fun createProductItemsList(count: Int): List<ProductItemResponse> {
        return (0 until count).map {
            ProductItemResponse(
                id = it,
                title = "Test Product Title ${it}",
                price = it + 1 + 0.5,
                description = "Test Product Description ${it}",
                category = "Test category ${it}",
                image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                rating = Rating(
                    rate = 4.2,
                    count = it
                )
            )
        }
    }

}