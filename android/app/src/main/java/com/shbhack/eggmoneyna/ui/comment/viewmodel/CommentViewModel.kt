package com.shbhack.eggmoneyna.ui.comment.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shbhack.eggmoneyna.data.model.CommentResponseDto
import com.shbhack.eggmoneyna.data.model.InputOutputDTO
import com.shbhack.eggmoneyna.data.model.WriteCommentRequest
import com.shbhack.eggmoneyna.data.repository.comment.CommentRepository
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "CommentViewModel_진영"

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val repository: CommentRepository
) : ViewModel() {
    private val _commentState =
        MutableStateFlow(CommentResponseDto(0, "", "", "", "", "", "", false))
    val commentState: StateFlow<CommentResponseDto> =
        _commentState.asStateFlow()

    private val _selectedItem = MutableStateFlow<InputOutputDTO?>(null)
    val selectedItem: StateFlow<InputOutputDTO?> = _selectedItem.asStateFlow()

    fun selectItem(item: InputOutputDTO) {
        Log.d(TAG, "selectItemId: $item")
        _selectedItem.value = item
    }


    fun getComment(inputOutputId: String) {
        Log.d(TAG, "getComment: 아이 : inputOutputId $inputOutputId")
        viewModelScope.launch {
            val response = repository.getComment(inputOutputId)
            Log.d(TAG, "getComment: $response")
            when (response) {
                is NetworkResponse.Success -> {
                    _commentState.emit(response.body)
                }

                else -> {
                    Log.d(TAG, "getComment: 통신 실패")
                }
            }
        }
    }


    // 부모
    fun getComment(childId: String, inputOutputId: String) {
        viewModelScope.launch {
            val response = repository.getParentComment(childId, inputOutputId)
            Log.d(TAG, "getComment: $response")
            when (response) {
                is NetworkResponse.Success -> {
                    _commentState.emit(response.body)
                }

                else -> {
                    Log.d(TAG, "getComment: 통신 실패")
                }
            }
        }
    }

    // 아이
    fun writeComment(inputOutputId: String, writeCommentRequest: WriteCommentRequest) {
        viewModelScope.launch {
            val response = repository.writeComment(inputOutputId, writeCommentRequest)
            Log.d(TAG, "writeComment: $response")
            when (response) {
                is NetworkResponse.Success -> {
                    _commentState.emit(response.body)
                }

                else -> {
                    Log.d(TAG, "writeComment: 통신 실패")
                }
            }
        }
    }

    // 부모
    fun writeComment(
        childId: String,
        inputOutputId: String,
        writeCommentRequest: WriteCommentRequest
    ) {
        viewModelScope.launch {
            val response =
                repository.writeParentComment(childId, inputOutputId, writeCommentRequest)
            Log.d(TAG, "writeComment: $response")
            when (response) {
                is NetworkResponse.Success -> {
                    _commentState.emit(response.body)
                }

                else -> {
                    Log.d(TAG, "writeComment: 통신 실패")
                }
            }
        }
    }
}