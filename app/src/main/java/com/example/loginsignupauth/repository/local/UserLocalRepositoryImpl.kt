package com.example.loginsignupauth.repository.local

import com.example.loginsignupauth.cache.AuthTokenPref
import com.example.loginsignupauth.db.UsersDataDao
import com.example.loginsignupauth.model.local.UserResponse
import javax.inject.Inject

class UserLocalRepositoryImpl @Inject constructor(
    private val authTokenPref: AuthTokenPref,
    private val usersDataDao: UsersDataDao
) : UserLocalRepository {

    override fun getAuthToken(): String? {
        return authTokenPref.getAuthToken()
    }

    override suspend fun saveUser(userResponse: UserResponse) {
        usersDataDao.saveUsers(userResponse)
    }

    override suspend fun saveAuthToken(authToken: String) {
        return authTokenPref.saveAuthToken(authToken = authToken)
    }

//    override suspend fun deleteUser() {
//        return usersDataDao.deleteUsers()
//    }

    override suspend fun clearAuthToken() {
        return authTokenPref.deleteAuthToken()
    }

    override fun getCurrentUserId(): String? {
        return usersDataDao.getUser()?.id
    }


}