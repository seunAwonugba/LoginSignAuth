package com.example.loginsignupauth.model.local

data class LoginResponse(
    val country: String,
    val email: String,
    val firstname: String,
    val id: String,
    val lastname: String,
    val primaryPhone: String,
    val token : String
)
