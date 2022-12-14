package com.example.loginsignupauth.repository.remote

import com.example.loginsignupauth.model.local.LoginRequest
import com.example.loginsignupauth.model.local.UserResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun login(data : LoginRequest) : Flow<UserResponse>

}