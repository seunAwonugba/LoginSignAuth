package com.example.loginsignupauth.model.remote


@kotlinx.serialization.Serializable
data class LoginResponseDto(
    val authority: Authority,
    val token: String,
    val user: User
){
    @kotlinx.serialization.Serializable
    data class Authority(
        val permissions: List<String> = emptyList(),
        val roles: List<String> = emptyList()
    )

    @kotlinx.serialization.Serializable
    data class User(
        val active: Boolean = false,
        val country: String = "",
        val createdAt: String = "",
        val deviceTokens: List<String> = emptyList(),
        val email: String = "",
        val firstname: String = "",
        val id: String = "",
        val lastname: String = "",
        val passwordExist: Boolean = false,
        val primaryPhone: String = "",
        val referrerCode: String = "",
        val roles: List<String> = emptyList(),
        val updatedAt: String = ""
    )

}