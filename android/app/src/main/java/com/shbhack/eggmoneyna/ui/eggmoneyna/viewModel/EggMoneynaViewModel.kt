package com.shbhack.eggmoneyna.ui.eggmoneyna.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shbhack.eggmoneyna.data.model.ComplimentDto
import com.shbhack.eggmoneyna.data.model.InputOutputsResponse
import com.shbhack.eggmoneyna.data.repository.main.MainRepository
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "EggMoneynaViewModel_진영"

@HiltViewModel
class EggMoneynaViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    private val _inputOutputsState = MutableStateFlow(InputOutputsResponse(emptyList()))
    val inputOutputsState: StateFlow<InputOutputsResponse> = _inputOutputsState.asStateFlow()

    private val _complimentsState = MutableStateFlow<List<ComplimentDto>>(emptyList())
    val complimentsState: StateFlow<List<ComplimentDto>> = _complimentsState.asStateFlow()

    fun getInputOutput(date: String) {
        viewModelScope.launch {
            val response = repository.getInputOutput(date)
            Log.d(TAG, "getInputOutput: $response")
            when (response) {
                is NetworkResponse.Success -> {
                    _inputOutputsState.emit(response.body)
                }

                else -> {
                    Log.d(TAG, "getInputOutput: 통신 실패")
                }
            }
        }
    }

    fun getCompliments(date: String) {
        viewModelScope.launch {
            val response = repository.getCompliment(date)
            Log.d(TAG, "getCompliments: $response")
            when (response) {
                is NetworkResponse.Success -> {
                    _complimentsState.emit(response.body)
                }

                else -> {
                    Log.d(TAG, "getCompliments: 통신 실패")
                }
            }
        }
    }
}