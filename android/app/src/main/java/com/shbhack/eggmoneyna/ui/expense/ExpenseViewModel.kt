package com.shbhack.eggmoneyna.ui.expense

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shbhack.eggmoneyna.data.model.MonthGraphResponseDto
import com.shbhack.eggmoneyna.data.model.WeekGraphResponseDto
import com.shbhack.eggmoneyna.data.repository.expense.ExpenseRepository
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ExpenseViewModel_진영"

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val repository: ExpenseRepository
) : ViewModel() {

    private val _weekGraphState = MutableStateFlow<List<WeekGraphResponseDto>>(emptyList())
    val weekGraphState: StateFlow<List<WeekGraphResponseDto>> = _weekGraphState.asStateFlow()

    private val _monthGraphState =
        MutableStateFlow(MonthGraphResponseDto(0.0, 0.0, 0.0, 0.0, 0.0, 0.0))
    val monthGraphState: StateFlow<MonthGraphResponseDto> = _monthGraphState.asStateFlow()

    fun getWeekGraph() {
        viewModelScope.launch {
            val response = repository.getWeekGraph()
            Log.d(TAG, "getWeekGraph: $response")
            when (response) {
                is NetworkResponse.Success -> {
                    _weekGraphState.emit(response.body)
                }

                else -> {
                    Log.d(TAG, "getWeekGraph: 통신 실패")
                }
            }
        }
    }

    fun getMonthGraph() {
        viewModelScope.launch {
            val response = repository.getMonthGraph()
            Log.d(TAG, "getMonthGraph: $response")
            when (response) {
                is NetworkResponse.Success -> {
                    _monthGraphState.emit(response.body)
                }

                else -> {
                    Log.d(TAG, "getMonthGraph: 통신 실패")
                }
            }
        }
    }
}