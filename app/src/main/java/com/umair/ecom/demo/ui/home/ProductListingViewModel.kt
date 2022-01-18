package com.umair.ecom.demo.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umair.ecom.demo.data.models.ProductItemResponse
import com.umair.ecom.demo.data.onEmpty
import com.umair.ecom.demo.data.onError
import com.umair.ecom.demo.data.onLoading
import com.umair.ecom.demo.data.onSuccess
import com.umair.ecom.demo.data.usecases.FetchAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListingViewModel @Inject constructor(
    private val loadAllProductsUseCase: FetchAllProductsUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<ProductUIState>()
    val uiState: LiveData<ProductUIState> = _uiState

    private val _productsList = MutableLiveData<List<ProductItemResponse>>()
    val productsList: LiveData<List<ProductItemResponse>> = _productsList

    fun loadProducts() {
        viewModelScope.launch {
            loadAllProductsUseCase.invoke(Unit).collect { dataResource ->
                dataResource.onSuccess {
                    _productsList.value = this.data!!
                    _uiState.value = ContentState
                }.onLoading {
                    _uiState.value = Loading
                }.onEmpty {
                    _uiState.value = EmptyState
                }.onError {
                    _uiState.value = Error(this.exception.message!!)
                }
            }
        }
    }


}