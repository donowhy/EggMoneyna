package com.shbhack.eggmoneyna.data.repository.main

import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.MonsterResponseDto
import com.shbhack.eggmoneyna.data.model.MonsterSaveResponseDto
import com.shbhack.eggmoneyna.data.remote.ApiService
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MainRepository {
    override suspend fun save(): NetworkResponse<MonsterSaveResponseDto, ErrorResponse> {
        return apiService.save()
    }

    override suspend fun getMyMong(): NetworkResponse<MonsterResponseDto, ErrorResponse> {
        return apiService.getMyMong()
    }

}