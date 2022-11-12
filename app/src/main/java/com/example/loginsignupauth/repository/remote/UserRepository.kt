package com.example.loginsignupauth.repository.remote

import androidx.lifecycle.LiveData
import com.example.loginsignupauth.model.local.UserResponse

interface UserRepository {

    suspend fun saveAuthToken(authToken: String)

    suspend fun saveUser(userResponse: UserResponse)
//
//    fun getUser(): LiveData<UserResponse?>
//
//    suspend fun deleteUser(userResponse: UserResponse)

//
//    fun logUserOut(): Flow<Unit>

}