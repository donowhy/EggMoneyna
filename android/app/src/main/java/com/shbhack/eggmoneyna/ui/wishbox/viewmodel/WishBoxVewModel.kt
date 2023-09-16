package com.shbhack.eggmoneyna.ui.wishbox.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shbhack.eggmoneyna.data.model.WishBoxInfoDto
import com.shbhack.eggmoneyna.data.model.WishBoxInfoResponseDto
import com.shbhack.eggmoneyna.data.repository.main.MainRepository
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishBoxVewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    private val _myWishAccount = MutableStateFlow(0L)
    var myWishAccount : StateFlow<Long> = _myWishAccount.asStateFlow()

    private val _myWishInfo = MutableStateFlow(WishBoxInfoResponseDto())
    var myWishInfo : StateFlow<WishBoxInfoResponseDto> = _myWishInfo.asStateFlow()

    fun setWishAccountValue(newAccount : Long) {
        _myWishAccount.value = newAccount
    }

    fun getWishInfo() {
        viewModelScope.launch {
            val response = repository.getWishBoxInfo(WishBoxInfoDto(_myWishAccount.value))
            when (response) {
                is NetworkResponse.Success -> {
                    _myWishInfo.emit(response.body)
                }
                else -> {
                    Log.d("위시박스정보", "getWishInfo: 통신 실패")
                }
            }
        }
    }
}