package com.example.loginsignupauth.repository.local

import com.example.loginsignupauth.model.local.UserResponse

interface UserLocalRepository {

    fun getAuthToken(): String?

    suspend fun saveUser(userResponse: UserResponse)

    suspend fun saveAuthToken(authToken: String)

//    suspend fun deleteUser()

    suspend fun clearAuthToken()

    fun getCurrentUserId(): String?


}