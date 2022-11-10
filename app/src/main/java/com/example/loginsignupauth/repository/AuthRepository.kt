package com.example.loginsignupauth.repository

import com.example.loginsignupauth.model.local.LoginRequest
import com.example.loginsignupauth.model.local.LoginResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun login(data : LoginRequest) : Flow<LoginResponse>
}