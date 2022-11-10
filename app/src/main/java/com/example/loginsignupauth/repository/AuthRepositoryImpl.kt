package com.example.loginsignupauth.repository

import com.example.loginsignupauth.model.local.LoginRequest
import com.example.loginsignupauth.model.local.LoginResponse
import com.example.loginsignupauth.model.mappers.toLoginRequestDto
import com.example.loginsignupauth.model.mappers.toLoginResponse
import com.example.loginsignupauth.network.AuthWebService
import com.example.loginsignupauth.repository.local.UserLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authWebService: AuthWebService,
    private val userLocalRepository: UserLocalRepository
) : AuthRepository {

    override fun login(data: LoginRequest): Flow<LoginResponse> {
        return flow {
            val response = authWebService.login(body = data.toLoginRequestDto()).toLoginResponse()
            userLocalRepository.saveAuthToken(response.token)
            emit(response)
        }
    }
}