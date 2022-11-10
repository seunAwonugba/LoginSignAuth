package com.example.loginsignupauth.repository.local

import com.example.loginsignupauth.cache.AuthTokenPref
import javax.inject.Inject

class UserLocalRepositoryImpl @Inject constructor(
    private val authTokenPref: AuthTokenPref,
) : UserLocalRepository {

    override suspend fun saveAuthToken(authToken: String) {
        authTokenPref.saveAuthToken(authToken = authToken)
    }


}