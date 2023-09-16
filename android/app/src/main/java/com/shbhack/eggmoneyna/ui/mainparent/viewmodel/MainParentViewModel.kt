package com.shbhack.eggmoneyna.ui.mainparent.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shbhack.eggmoneyna.data.model.GetAllUnActivatedChildResponseDto
import com.shbhack.eggmoneyna.data.model.MyChildInfoResponseDto
import com.shbhack.eggmoneyna.data.repository.main.MainRepository
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainParentViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    private val _myChildren = MutableStateFlow(emptyList<MyChildInfoResponseDto>())
    var myChildren: StateFlow<List<MyChildInfoResponseDto>> = _myChildren.asStateFlow()

    fun myChildInfo() {
        viewModelScope.launch {
            val response = repository.myChildInfo()
            when (response) {
                is NetworkResponse.Success -> {
                    _myChildren.emit(response.body)
                }
                else -> {
                    Log.d("부모 홈 자녀리스트", "myChildInfo: 통신 실패")
                }
            }
        }
    }
}
