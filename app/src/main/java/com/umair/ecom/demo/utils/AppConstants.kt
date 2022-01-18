package com.umair.ecom.demo.utils

object AppConstants {
    object APIConfig {
        const val BASE_URL = "https://fakestoreapi.com/"
        const val TIMEOUT_DEFAULT = 30L
    }

    object APIEndpoints {
        const val GET_PRODUCT_LISTING = "products"
        const val GET_PRODUCT_DETAILS = "products/{id}"
    }

    object ParcelKeys {
        const val PRODUCT_ITEM = "product_item_model_key"
    }
}