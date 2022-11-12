package com.example.loginsignupauth.network

import com.example.loginsignupauth.model.remote.LoginRequestDto
import com.example.loginsignupauth.model.remote.LoginResponseDto
import com.example.loginsignupauth.model.remote.UserResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthWebService {

    @POST("auth/login")
    suspend fun login(
        @Body body: LoginRequestDto
    ) : LoginResponseDto
}