package com.example.loginsignupauth.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginsignupauth.model.local.UserResponse
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
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    init {
        getUser()
    }


    private fun getUser(){
        userRepository.getUserById()
            .wrapAsResource()
            .onEach {
                Log.e("USER_DETAILS", it.toString())
                _state.updateValue {
                    copy(userRes = it)
                }
            }
            .launchIn(viewModelScope)
    }



    companion object {
        data class State(
            val userRes: Resource<UserResponse> = Resource.initial(),
        )
    }

}