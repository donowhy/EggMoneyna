package com.shbhack.eggmoneyna.data.remote

import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.MonsterResponseDto
import com.shbhack.eggmoneyna.data.model.MonsterSaveResponseDto
import com.shbhack.eggmoneyna.data.model.ParentSaveDto
import com.shbhack.eggmoneyna.data.model.ParentSaveResponseDto
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    // 부모 생성 및 계좌 생성
    @POST("/parent/save")
    suspend fun parentSave(@Body parentSaveDto: ParentSaveDto): NetworkResponse<ParentSaveResponseDto, ErrorResponse>

    // 신한몽
    @POST("/monsters/save")
    suspend fun save(): NetworkResponse<MonsterSaveResponseDto, ErrorResponse>

    @GET("/monsters/getMyMong")
    suspend fun getMyMong(): NetworkResponse<MonsterResponseDto, ErrorResponse>
}