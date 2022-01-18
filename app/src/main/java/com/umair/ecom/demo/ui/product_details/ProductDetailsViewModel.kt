package com.umair.ecom.demo.ui.product_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umair.ecom.demo.data.onEmpty
import com.umair.ecom.demo.data.onError
import com.umair.ecom.demo.data.onLoading
import com.umair.ecom.demo.data.onSuccess
import com.umair.ecom.demo.data.remote.responses.ProductItemResponse
import com.umair.ecom.demo.data.usecases.FetchProductDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val loadProductDetailsUseCase: FetchProductDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<ProductDetailsUIState>()
    val uiState: LiveData<ProductDetailsUIState> = _uiState

    private val _product = MutableLiveData<ProductItemResponse>()
    val product: LiveData<ProductItemResponse> = _product

    fun initAnimation(item: ProductItemResponse) {
        _product.value = item
    }

    fun showContent() {
        _uiState.value = ContentState
    }

    fun showError(message: String) {
        _uiState.value = Error(message)
    }

    fun loadProductDetails(id: Int) {
        viewModelScope.launch {
            loadProductDetailsUseCase.invoke(id).collect { dataResource ->
                dataResource.onSuccess {
                    _product.value = this.data!!
                    _uiState.value = ContentState
                }.onLoading {
                    _uiState.value = Loading
                }.onEmpty {
                    _uiState.value = EmptyState
                }.onError {
                    _uiState.value = Error(this.exception.message ?: "Unknown")
                }
            }
        }
    }


}