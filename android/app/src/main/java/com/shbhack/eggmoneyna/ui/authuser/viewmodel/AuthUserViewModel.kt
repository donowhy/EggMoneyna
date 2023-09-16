package com.shbhack.eggmoneyna.ui.authuser.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shbhack.eggmoneyna.data.local.AppPreferences
import com.shbhack.eggmoneyna.data.model.checkAuthDto
import com.shbhack.eggmoneyna.data.model.checkParentAuthResponseDto
import com.shbhack.eggmoneyna.data.model.send1WonDto
import com.shbhack.eggmoneyna.data.repository.main.MainRepository
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthUserViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    private val _account = MutableStateFlow(0L)
    var account: StateFlow<Long> = _account.asStateFlow()

    private val _authNum = MutableStateFlow(0)
    var authNum: StateFlow<Int> = _authNum.asStateFlow()

    private val _activated = MutableStateFlow(false)
    var activated : StateFlow<Boolean> = _activated.asStateFlow()

    fun setAccountValue(newAccount :Long) {
        _account.value = newAccount
    }

    fun setAuthNumValue(newAuth : Int) {
        _authNum.value = newAuth
    }


    fun send1Won(){
        viewModelScope.launch {
            val response = repository.send1Won(send1WonDto( account.value))
            Log.d("사용자인증", "getInputOutput: $response")
            when (response) {
                is NetworkResponse.Success -> {
                    _authNum.emit(response.body.certNumber)
                }
                else -> {
                    Log.d("사용자인증", "getParticipatedFestival: 통신 실패")
                }
            }
        }
    }

    fun checkParentAuth() {
        viewModelScope.launch {
            val response = repository.checkParentAuth(checkAuthDto(account.value, authNum.value))
            Log.d("사용자인증", "checkParentAuth: $response")
            when (response) {
                is NetworkResponse.Success -> {
                    if(response.body.isRight) {
                        AppPreferences.initToken(response.body.parentToken)
                    }
                }
                else -> {
                    Log.d("사용자인증", "checkParentAuth: 통신 실패")
                }
            }
        }
    }

    fun checkChildAuth() {
        viewModelScope.launch {
            val response = repository.checkChildAuth(checkAuthDto(account.value, authNum.value))
            when(response) {
                is NetworkResponse.Success -> {
                    _activated.value = response.body.isAccountActivate
                    if (response.body.isRight && response.body.isAccountActivate) {
                        AppPreferences.initToken(response.body.childToken)
                    }
                }
                else -> {
                    Log.d("사용자인증", "checkChildAuth: 통신 실패")
                }
            }
        }
    }
}