package com.shbhack.eggmoneyna.ui.mainchild.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shbhack.eggmoneyna.data.model.ChildHomeInfoResponseDto
import com.shbhack.eggmoneyna.data.repository.main.MainRepository
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainChildViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    private val _mainInfo = MutableStateFlow(ChildHomeInfoResponseDto())
    var mainInfo: StateFlow<ChildHomeInfoResponseDto> = _mainInfo.asStateFlow()

    fun childHomeInfo() {
        viewModelScope.launch {
            val response = repository.childHomeInfo("")
            when (response) {
                is NetworkResponse.Success -> {
                    _mainInfo.emit(response.body)
                }
                else -> {
                    Log.d("자녀 홈 정보", "childHomeInfo: 통신 실패")
                }
            }
        }
    }
}