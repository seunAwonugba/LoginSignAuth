package com.example.loginsignupauth.di

import com.example.loginsignupauth.constants.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitProvider {
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun providerConverterFactory(): Converter.Factory {
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        webServiceClient: CoreOkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(webServiceClient())
            .addConverterFactory(converterFactory)
            .build()
    }
}