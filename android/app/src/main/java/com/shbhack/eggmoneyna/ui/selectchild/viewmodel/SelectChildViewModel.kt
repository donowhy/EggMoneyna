package com.shbhack.eggmoneyna.ui.selectchild.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shbhack.eggmoneyna.data.model.GetAllUnActivatedChildResponseDto
import com.shbhack.eggmoneyna.data.repository.main.MainRepository
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SelectChildViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    private val _myChildren = MutableStateFlow(emptyList<GetAllUnActivatedChildResponseDto>())
    var myChildren: StateFlow<List<GetAllUnActivatedChildResponseDto>> = _myChildren.asStateFlow()

    fun getAllUnActivatedChild() {
        viewModelScope.launch {
            val response = repository.getAllUnActivatedChild()
            when (response) {
                is NetworkResponse.Success -> {
                    _myChildren.emit(response.body)
                }
                else -> {
                    Log.d("자녀 선택", "getAllUnActivatedChild: 통신 실패")
                }
            }
        }
    }

    fun activatingChild(id : Long) {
        viewModelScope.launch {
            val response = repository.activatingChild(id)
            when (response) {
                is NetworkResponse.Success -> {
                    Log.d("자녀 활성화", "activatingChild: 활성화 성공")
                }
                else -> {
                    Log.d("자녀 활성화", "activatingChild: 통신 실패")
                }
            }
        }
    }
}