package com.example.loginsignupauth.cache

import com.example.loginsignupauth.constants.Constants.AUTH_TOKEN_SHARED_PREF_KEY
import com.example.loginsignupauth.core.CoreSharedPrefManager
import javax.inject.Inject

class AuthTokenPref @Inject constructor(private val coreSharedPrefManager: CoreSharedPrefManager) {

    fun getAuthToken(): String? {
        return coreSharedPrefManager.getPref<String>(AUTH_TOKEN_SHARED_PREF_KEY)
    }

    fun saveAuthToken(authToken: String) {
        coreSharedPrefManager.savePref(AUTH_TOKEN_SHARED_PREF_KEY, authToken)
    }

    fun deleteAuthToken() {
        coreSharedPrefManager.deletePref(AUTH_TOKEN_SHARED_PREF_KEY)
    }


}