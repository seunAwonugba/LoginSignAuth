package com.example.loginsignupauth.network

import com.example.loginsignupauth.model.remote.UserResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UserWebService {

    @GET("user/{id}")
    suspend fun fetchUserWithId(
        @Path("id") id:String
    ): UserResponseDto
}