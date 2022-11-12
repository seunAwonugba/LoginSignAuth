package com.example.loginsignupauth.model.mappers

import com.example.loginsignupauth.model.local.LoginRequest
import com.example.loginsignupauth.model.local.LoginResponse
import com.example.loginsignupauth.model.local.UserResponse
import com.example.loginsignupauth.model.remote.LoginRequestDto
import com.example.loginsignupauth.model.remote.LoginResponseDto
import com.example.loginsignupauth.model.remote.UserResponseDto

fun LoginResponseDto.toLoginResponse() = LoginResponse(
    country = this.user.country,
    email = this.user.email,
    firstname = this.user.firstname,
    id = this.user.id,
    lastname = this.user.lastname,
    primaryPhone = this.user.primaryPhone,
    token = this.token
)


fun LoginRequest.toLoginRequestDto() = LoginRequestDto(
    email = this.email,
    password = this.password
)

fun UserResponseDto.toUsersResponse() = UserResponse(
    active, country, address, state, createdAt, createdBy, email, firstname, id, lastname, primaryPhone, updatedAt, imageUrl
)