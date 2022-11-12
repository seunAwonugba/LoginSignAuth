package com.example.loginsignupauth.di

import android.util.Log
import com.example.loginsignupauth.BuildConfig
import com.example.loginsignupauth.cache.AuthTokenPref
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CoreOkHttpClient @Inject constructor(private val authTokenPref: AuthTokenPref) {

    private val okHttpClient: OkHttpClient by lazy { buildOkHttpClient() }


    private fun buildOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(makeLoggingInterceptor())
            .addInterceptor(AuthInterceptor(authTokenPref))
            .addInterceptor(ErrorInterceptor()) // always add this last, so okhttp executes it last
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    operator fun invoke(): OkHttpClient {
        return okHttpClient
    }

    class AuthInterceptor(private var tokenManager: AuthTokenPref) : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val requestBuilder = request.newBuilder()
            tokenManager.getAuthToken()?.let {
                if (BuildConfig.DEBUG) Log.d(TAG, "AUTH TOKEN $it")
                requestBuilder.addHeader("Authorization", "Bearer $it")
            }
            return chain.proceed(requestBuilder.build())
        }

        companion object {
            private val TAG = AuthInterceptor::class.java.canonicalName
        }
    }

    class ErrorInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val response = chain.proceed(chain.request())
            if (response.isSuccessful.not()) {
                val errorMsg = getErrorMessage(response.body)
                throw ServerErrorException(errorMsg)
            }
            return response
        }

        private fun getErrorMessage(responseBody: ResponseBody?): String {
            return try {
                val body = JSONObject(responseBody?.string() ?: "")
                return if (body.has("error") && body.has("message")) {
                    "${body.getString("error")} - ${body.getString("message")}"
                } else {
                    body.toString()
                }
            } catch (e: JSONException) {
                "Unknown error occurred"
            }
        }

        class ServerErrorException(message: String) : IOException(message)
    }
}