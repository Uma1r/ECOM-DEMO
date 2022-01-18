package com.umair.ecom.demo.di

import com.umair.ecom.demo.data.repository.ProductsRepository
import com.umair.ecom.demo.data.usecases.FetchAllProductsUseCase
import com.umair.ecom.demo.data.usecases.FetchProductDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesModule {

    @ViewModelScoped
    @Provides
    fun provideFetchAllProductsUseCase(
        repository: ProductsRepository
    ): FetchAllProductsUseCase {
        return FetchAllProductsUseCase(repository, Dispatchers.IO)
    }

    @ViewModelScoped
    @Provides
    fun provideFetchProductDetailsUseCase(
        repository: ProductsRepository
    ): FetchProductDetailsUseCase {
        return FetchProductDetailsUseCase(repository, Dispatchers.IO)
    }
}