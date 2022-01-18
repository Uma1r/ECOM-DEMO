package com.umair.ecom.demo.ui.home

sealed class ProductUIState

object Loading: ProductUIState()
object ContentState: ProductUIState()
object EmptyState: ProductUIState()
class Error(val message: String): ProductUIState()