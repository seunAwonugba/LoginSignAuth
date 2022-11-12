package com.example.loginsignupauth.repository.remote.impl

import com.example.loginsignupauth.model.local.LoginRequest
import com.example.loginsignupauth.model.local.LoginResponse
import com.example.loginsignupauth.model.mappers.toLoginRequestDto
import com.example.loginsignupauth.model.mappers.toLoginResponse
import com.example.loginsignupauth.network.AuthWebService
import com.example.loginsignupauth.repository.remote.AuthRepository
import com.example.loginsignupauth.repository.remote.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authWebService: AuthWebService,
    private val userRepository: UserRepository
) : AuthRepository {

    override fun login(data: LoginRequest): Flow<LoginResponse> {
        return flow {
            val response = authWebService.login(body = data.toLoginRequestDto()).toLoginResponse()
            userRepository.saveAuthToken(response.token)
            emit(response)
        }
    }




}