package com.shbhack.eggmoneyna.data.remote

import com.shbhack.eggmoneyna.data.model.ComplimentDto
import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.InputOutputsResponse
import com.shbhack.eggmoneyna.data.model.MonsterResponseDto
import com.shbhack.eggmoneyna.data.model.MonsterSaveResponseDto
import com.shbhack.eggmoneyna.data.model.MonthGraphResponseDto
import com.shbhack.eggmoneyna.data.model.ParentSaveDto
import com.shbhack.eggmoneyna.data.model.ParentSaveResponseDto
import com.shbhack.eggmoneyna.data.model.RelationResponse
import com.shbhack.eggmoneyna.data.model.WeekGraphResponseDto
import com.shbhack.eggmoneyna.data.model.WriteCommentRequest
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    // 부모 생성 및 계좌 생성
    @POST("parent/save")
    suspend fun parentSave(@Body parentSaveDto: ParentSaveDto): NetworkResponse<ParentSaveResponseDto, ErrorResponse>

    // 자녀 계정 활성화
    @POST("relations/createEggMoney/{childId}")
    suspend fun createEggMoney(@Path("childId") childId: Int): NetworkResponse<RelationResponse, ErrorResponse>


    // 신한몽
    @POST("monsters/save")
    suspend fun save(): NetworkResponse<MonsterSaveResponseDto, ErrorResponse>

    @GET("monsters/getMyMong")
    suspend fun getMyMong(): NetworkResponse<MonsterResponseDto, ErrorResponse>


    // 당일 입출금 조회
    @POST("inputoutput/all/{inputOuputDate}")
    suspend fun getInputOutput(@Path("inputOuputDate") inputOuputDate: String): NetworkResponse<InputOutputsResponse, ErrorResponse>

    // 코멘트
    @POST("comment/{inputOutputId}")
    suspend fun writeComment(
        @Path("inputOutputDate") inputOutputDate: String,
        @Body writeCommentRequest: WriteCommentRequest
    ): NetworkResponse<MonsterResponseDto, ErrorResponse>

    // 월 칭찬 조회 (yyyy-MM)
    @POST("compliment/month/{inputOutputDate}")
    suspend fun getCompliment(@Path("inputOutputDate") inputOuputDate: String): NetworkResponse<List<ComplimentDto>, ErrorResponse>

    @POST("graph/week")
    suspend fun getWeekGraph(): NetworkResponse<List<WeekGraphResponseDto>, ErrorResponse>

    @POST("/v1/graph/category")
    suspend fun getMonthGraph(): NetworkResponse<MonthGraphResponseDto, ErrorResponse>
}