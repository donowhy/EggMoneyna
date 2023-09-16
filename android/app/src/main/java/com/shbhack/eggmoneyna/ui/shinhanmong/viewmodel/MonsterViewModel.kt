package com.shbhack.eggmoneyna.ui.shinhanmong.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shbhack.eggmoneyna.data.model.MonsterResponseDto
import com.shbhack.eggmoneyna.data.repository.monster.MonsterRepository
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MonsterViewModel_진영"

@HiltViewModel
class MonsterViewModel @Inject constructor(
    private val repository: MonsterRepository
) : ViewModel() {
    private val _monsterState = MutableStateFlow<MonsterResponseDto?>(null)
    val monsterState: StateFlow<MonsterResponseDto?> = _monsterState.asStateFlow()

    fun getMyMongDetail() {
        viewModelScope.launch {
            val response = repository.getMyMongDetail()
            Log.d(TAG, "getComment: $response")
            when (response) {
                is NetworkResponse.Success -> {
                    _monsterState.emit(response.body)
                }

                else -> {
                    Log.d(TAG, "getComment: 통신 실패")
                }
            }
        }
    }

}