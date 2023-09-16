package com.shbhack.eggmoneyna.data.repository.parent

import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.MonsterResponseDto
import com.shbhack.eggmoneyna.data.model.MonsterSaveResponseDto
import com.shbhack.eggmoneyna.data.model.ParentSaveDto
import com.shbhack.eggmoneyna.data.model.ParentSaveResponseDto
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import retrofit2.http.Body

interface ParentRepository {
    suspend fun parentSave(@Body parentSaveDto: ParentSaveDto): NetworkResponse<ParentSaveResponseDto, ErrorResponse>

}