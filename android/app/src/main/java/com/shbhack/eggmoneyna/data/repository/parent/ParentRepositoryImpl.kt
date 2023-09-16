package com.shbhack.eggmoneyna.data.repository.parent

import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.ParentSaveDto
import com.shbhack.eggmoneyna.data.model.ParentSaveResponseDto
import com.shbhack.eggmoneyna.data.model.RelationResponse
import com.shbhack.eggmoneyna.data.remote.ApiService
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import javax.inject.Inject

class ParentRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ParentRepository {

    override suspend fun parentSave(parentSaveDto: ParentSaveDto): NetworkResponse<ParentSaveResponseDto, ErrorResponse> {
        return apiService.parentSave(parentSaveDto)
    }

    override suspend fun createEggMoney(childId: Int): NetworkResponse<RelationResponse, ErrorResponse> {
        return apiService.createEggMoney(childId)
    }

}