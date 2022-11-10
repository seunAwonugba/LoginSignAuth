package com.example.loginsignupauth.network

import com.example.loginsignupauth.model.remote.LoginRequestDto
import com.example.loginsignupauth.model.remote.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthWebService {

    @POST("auth/login")
    suspend fun login(@Body body: LoginRequestDto) : LoginResponseDto
}