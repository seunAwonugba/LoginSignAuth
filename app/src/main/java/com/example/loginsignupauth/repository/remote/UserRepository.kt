package com.example.loginsignupauth.repository.remote

import com.example.loginsignupauth.model.local.UserResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun saveAuthToken(authToken: String)

    suspend fun saveUser(userResponse: UserResponse)

    fun getAuthToken(): String?

    fun getUserById(): Flow<UserResponse>
//
//    suspend fun deleteUser(userResponse: UserResponse)

//
//    fun logUserOut(): Flow<Unit>

}