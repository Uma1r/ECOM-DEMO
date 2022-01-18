package com.umair.ecom.demo.di

import android.content.Context
import com.umair.ecom.demo.data.remote.FakeStoreApiService
import com.umair.ecom.demo.data.repository.DefaultProductsRepository
import com.umair.ecom.demo.data.repository.ProductsRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideProductsRepository(
        apiService: FakeStoreApiService
    ): ProductsRepository {
        return DefaultProductsRepository(
            apiService,
            Dispatchers.IO
        )
    }
}