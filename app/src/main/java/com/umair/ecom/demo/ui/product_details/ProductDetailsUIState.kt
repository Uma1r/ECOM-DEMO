package com.umair.ecom.demo.ui.product_details

sealed class ProductDetailsUIState

object Loading: ProductDetailsUIState()
object ContentState: ProductDetailsUIState()
object EmptyState: ProductDetailsUIState()
class Error(val message: String): ProductDetailsUIState()