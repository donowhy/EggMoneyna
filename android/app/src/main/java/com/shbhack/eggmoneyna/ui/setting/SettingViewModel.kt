package com.shbhack.eggmoneyna.ui.setting

import androidx.lifecycle.ViewModel
import com.shbhack.eggmoneyna.data.repository.main.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {
    private val _thresholdState = MutableStateFlow(0)
    val thresholdState: StateFlow<Int> = _thresholdState.asStateFlow()
}