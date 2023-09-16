package com.shbhack.eggmoneyna.data.repository.parent

import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.MonsterResponseDto
import com.shbhack.eggmoneyna.data.model.MonsterSaveResponseDto
import com.shbhack.eggmoneyna.data.model.ParentSaveDto
import com.shbhack.eggmoneyna.data.model.ParentSaveResponseDto
import com.shbhack.eggmoneyna.data.model.RelationResponse
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.Path

interface ParentRepository {
    // 부모 생성 및 계좌 생성
    suspend fun parentSave(@Body parentSaveDto: ParentSaveDto): NetworkResponse<ParentSaveResponseDto, ErrorResponse>

    // 자녀 계좌 활성화
    suspend fun createEggMoney(@Path("childId") childId: Int): NetworkResponse<RelationResponse, ErrorResponse>
}