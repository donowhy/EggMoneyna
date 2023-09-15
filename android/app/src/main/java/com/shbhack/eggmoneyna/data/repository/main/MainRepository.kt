package com.shbhack.eggmoneyna.data.repository.main

import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.MonsterResponseDto
import com.shbhack.eggmoneyna.data.model.MonsterSaveResponseDto
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface MainRepository {
    // 신한몽
    suspend fun save(): NetworkResponse<MonsterSaveResponseDto, ErrorResponse>

    suspend fun getMyMong(): NetworkResponse<MonsterResponseDto, ErrorResponse>
}