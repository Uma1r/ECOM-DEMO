package com.umair.ecom.demo.di

import com.umair.ecom.demo.data.remote.FakeStoreApiService
import com.umair.ecom.demo.data.repository.ProductsRepositoryImpl
import com.umair.ecom.demo.data.repository.ProductsRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideProductsRepository(
        apiService: FakeStoreApiService
    ): ProductsRepository {
        return ProductsRepositoryImpl(
            apiService
        )
    }
}