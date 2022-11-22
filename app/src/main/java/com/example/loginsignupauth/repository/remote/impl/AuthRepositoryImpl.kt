package com.example.loginsignupauth.repository.remote.impl

import android.util.Log
import com.example.loginsignupauth.model.local.LoginRequest
import com.example.loginsignupauth.model.local.UserResponse
import com.example.loginsignupauth.model.mappers.loginResponseDtoToUserResponse
import com.example.loginsignupauth.model.mappers.toLoginRequestDto
import com.example.loginsignupauth.model.remote.LoginResponseDto
import com.example.loginsignupauth.network.AuthWebService
import com.example.loginsignupauth.repository.remote.AuthRepository
import com.example.loginsignupauth.repository.remote.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authWebService: AuthWebService,
    private val userRepository: UserRepository
) : AuthRepository {

    override fun login(data: LoginRequest): Flow<UserResponse> {
        return flow {
            val response = authWebService.login(body = data.toLoginRequestDto())
            handleUserResponse(response)
        }
    }

    private suspend fun FlowCollector<UserResponse>.handleUserResponse(response: LoginResponseDto) {
        val user = response.loginResponseDtoToUserResponse()
        userRepository.saveUser(user)
        userRepository.saveAuthToken(response.token)
        emit(user)
    }
}