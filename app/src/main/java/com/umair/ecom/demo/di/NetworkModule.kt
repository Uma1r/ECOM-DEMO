package com.umair.ecom.demo.di

import android.content.Context
import com.umair.ecom.demo.data.remote.FakeStoreApiService
import com.umair.ecom.demo.data.remote.NoConnectionInterceptor
import com.umair.ecom.demo.utils.AppConstants
import com.umair.ecom.demo.utils.StringUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(NoConnectionInterceptor(context))
            .connectTimeout(AppConstants.APIConfig.TIMEOUT_DEFAULT, TimeUnit.SECONDS)
            .readTimeout(AppConstants.APIConfig.TIMEOUT_DEFAULT, TimeUnit.SECONDS)
            .writeTimeout(AppConstants.APIConfig.TIMEOUT_DEFAULT, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.APIConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesFakeStoreApiService(retrofit: Retrofit): FakeStoreApiService {
        return retrofit.create(FakeStoreApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideStringUtils(@ApplicationContext context: Context): StringUtils {
        return StringUtils(context)
    }
}