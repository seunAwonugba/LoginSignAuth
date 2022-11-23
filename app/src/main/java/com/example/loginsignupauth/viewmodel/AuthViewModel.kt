package com.example.loginsignupauth.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginsignupauth.model.local.LoginRequest
import com.example.loginsignupauth.model.local.UserResponse
import com.example.loginsignupauth.repository.remote.AuthRepository
import com.example.loginsignupauth.repository.remote.UserRepository
import com.example.loginsignupauth.utils.Resource
import com.example.loginsignupauth.utils.updateValue
import com.example.loginsignupauth.utils.wrapAsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {


    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state


    fun login(data : LoginRequest){
        authRepository.login(data = data)
            .wrapAsResource()
            .onEach {
                _state.updateValue {
                    copy(loginRes = it)
                }
            }
            .launchIn(viewModelScope)
    }

    fun isLoggedIn() : Boolean{
        return userRepository.getAuthToken() != null
    }


    companion object {
        data class State(
            val loginRes: Resource<UserResponse> = Resource.initial(),
        )
    }
}