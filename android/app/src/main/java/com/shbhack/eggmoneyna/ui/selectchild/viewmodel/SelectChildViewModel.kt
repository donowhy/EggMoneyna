package com.shbhack.eggmoneyna.ui.selectchild.viewmodel

import androidx.lifecycle.ViewModel
import com.shbhack.eggmoneyna.data.model.getAllUnActivatedChildResponseDto
import com.shbhack.eggmoneyna.data.repository.main.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class SelectChildViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    private val _myChildren = MutableStateFlow(emptyList<getAllUnActivatedChildResponseDto>())
    var myChildren: StateFlow<List<getAllUnActivatedChildResponseDto>> = _myChildren.asStateFlow()
}