package com.example.loginsignupauth.di

import com.example.loginsignupauth.network.AuthWebService
import com.example.loginsignupauth.repository.AuthRepository
import com.example.loginsignupauth.repository.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {



    @Singleton
    @Provides
    fun provideAuthWebService(retrofit: Retrofit) : AuthWebService = retrofit.create()


    @Singleton
    @Provides
    fun provideAuthRepository(
        authWebService: AuthWebService
    ) : AuthRepository = AuthRepositoryImpl(authWebService)
}