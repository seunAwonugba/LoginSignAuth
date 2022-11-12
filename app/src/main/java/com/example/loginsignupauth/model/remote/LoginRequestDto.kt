package com.example.loginsignupauth.model.remote


@kotlinx.serialization.Serializable
data class LoginRequestDto(
    val email : String,
    val password : String
)
