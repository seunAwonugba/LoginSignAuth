package com.example.loginsignupauth.model.remote

@kotlinx.serialization.Serializable
data class UserResponseDto(
    val active: Boolean = false,
    val country: String = "",
    val address:String = "",
    val state:String = "",
    val createdAt: String = "",
    val createdBy: String = "",
    val email: String = "",
    val firstname: String = "",
    val id: String = "",
    val lastname: String = "",
    val primaryPhone: String = "",
    val updatedAt: String = "",
    val imageUrl: String = ""
)