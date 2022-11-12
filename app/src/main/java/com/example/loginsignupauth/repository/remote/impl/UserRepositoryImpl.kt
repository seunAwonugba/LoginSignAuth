package com.example.loginsignupauth.repository.remote.impl

import com.example.loginsignupauth.model.local.UserResponse
import com.example.loginsignupauth.network.UserWebService
import com.example.loginsignupauth.repository.local.UserLocalRepository
import com.example.loginsignupauth.repository.remote.UserRepository
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