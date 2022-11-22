package com.example.loginsignupauth.repository.remote.impl

import android.util.Log
import com.example.loginsignupauth.model.local.UserResponse
import com.example.loginsignupauth.model.mappers.toUsersResponse
import com.example.loginsignupauth.network.UserWebService
import com.example.loginsignupauth.repository.local.UserLocalRepository
import com.example.loginsignupauth.repository.remote.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalRepository: UserLocalRepository,
    private val userWebService: UserWebService
) : UserRepository {

    override suspend fun saveAuthToken(authToken: String) {
        userLocalRepository.saveAuthToken(authToken = authToken)
    }

    override suspend fun saveUser(userResponse: UserResponse) {
        userLocalRepository.saveUser(userResponse = userResponse)
    }

    override fun getAuthToken(): String? {
        return userLocalRepository.getAuthToken()
    }

    override fun getUserById(): Flow<UserResponse> {
        return flow {
            val userId = userLocalRepository.getCurrentUserId()
            val response = userWebService.fetchUserWithId(id = userId.toString() ).toUsersResponse()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
//
//    override fun getUser(): LiveData<UserResponse?> {
//        return usersDataDao.getUser()
//    }
//
//    override suspend fun deleteUser(userResponse: UserResponse) {
//        return usersDataDao.deleteUser()
//    }


//    override suspend fun saveUser(userResponse: UserResponse) {
//        userLocalRepository.saveUser(userResponse = userResponse)
//    }
//
//    override fun logUserOut(): Flow<Unit> {
//        return flow {
//            userLocalRepository.deleteUser()
//            emit(Unit)
//        }
//    }


}