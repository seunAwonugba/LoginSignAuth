package com.example.loginsignupauth.repository.local

interface UserLocalRepository {

    suspend fun saveAuthToken(authToken: String)
}