package com.example.loginsignupauth.di

import com.example.loginsignupauth.repository.local.UserLocalRepository
import com.example.loginsignupauth.repository.local.UserLocalRepositoryImpl
import com.example.loginsignupauth.repository.remote.AuthRepository
import com.example.loginsignupauth.repository.remote.UserRepository
import com.example.loginsignupauth.repository.remote.impl.AuthRepositoryImpl
import com.example.loginsignupauth.repository.remote.impl.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryBindings {

    @Binds
    @Singleton
    fun bindAuthRepository(repository: AuthRepositoryImpl): AuthRepository


    @Binds
    @Singleton
    fun bindUserRepository(repository: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    fun bindLocalRepo(repository: UserLocalRepositoryImpl): UserLocalRepository
}