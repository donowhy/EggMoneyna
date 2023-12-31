package com.shbhack.eggmoneyna.data.repository.monster

import com.shbhack.eggmoneyna.data.model.CollectionResponseDto
import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.MonsterResponseDto
import com.shbhack.eggmoneyna.data.model.MonsterSaveRequestDto
import com.shbhack.eggmoneyna.data.model.MonsterSaveResponseDto
import com.shbhack.eggmoneyna.data.remote.ApiService
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import javax.inject.Inject

class MonsterRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MonsterRepository {
    override suspend fun save(monsterSaveRequestDto: MonsterSaveRequestDto): NetworkResponse<MonsterSaveResponseDto, ErrorResponse> {
        return apiService.save(monsterSaveRequestDto)
    }

    override suspend fun getMyMongDetail(): NetworkResponse<MonsterResponseDto, ErrorResponse> {
        return apiService.getMyMongDetail()
    }

    override suspend fun getDogam(): NetworkResponse<List<CollectionResponseDto>, ErrorResponse> {
        return apiService.getDogam()
    }


}