package com.shbhack.eggmoneyna.data.remote

import com.shbhack.eggmoneyna.data.model.CommentResponseDto
import com.shbhack.eggmoneyna.data.model.ComplimentDto
import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.InputOutputsResponse
import com.shbhack.eggmoneyna.data.model.MonsterResponseDto
import com.shbhack.eggmoneyna.data.model.MonsterSaveRequestDto
import com.shbhack.eggmoneyna.data.model.MonsterSaveResponseDto
import com.shbhack.eggmoneyna.data.model.MonthGraphResponseDto
import com.shbhack.eggmoneyna.data.model.ParentSaveDto
import com.shbhack.eggmoneyna.data.model.ParentSaveResponseDto
import com.shbhack.eggmoneyna.data.model.RelationResponse
import com.shbhack.eggmoneyna.data.model.TotalMonthOutputResponse
import com.shbhack.eggmoneyna.data.model.WeekGraphResponseDto
import com.shbhack.eggmoneyna.data.model.WriteCommentRequest
import com.shbhack.eggmoneyna.data.model.checkAuthDto
import com.shbhack.eggmoneyna.data.model.checkChildAuthResponseDto
import com.shbhack.eggmoneyna.data.model.checkParentAuthResponseDto
import com.shbhack.eggmoneyna.data.model.getAllUnActivatedChildResponseDto
import com.shbhack.eggmoneyna.data.model.send1WonDto
import com.shbhack.eggmoneyna.data.model.send1WonResponseDto
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
    suspend fun save(@Body monsterSaveRequestDto: MonsterSaveRequestDto): NetworkResponse<MonsterSaveResponseDto, ErrorResponse>

    @GET("monsters/getMyMongDetail")
    suspend fun getMyMongDetail(): NetworkResponse<MonsterResponseDto, ErrorResponse>


    // 당일 입출금 조회
    @POST("inputoutput/all/{inputOutputDate}")
    suspend fun getInputOutput(@Path("inputOutputDate") inputOutputDate: String): NetworkResponse<InputOutputsResponse, ErrorResponse>

    // 코멘트

    // 코멘트 작성 (아이)
    @POST("comment/{inputOutputId}")
    suspend fun writeComment(
        @Path("inputOutputId") inputOutputId: String,
        @Body writeCommentRequest: WriteCommentRequest
    ): NetworkResponse<CommentResponseDto, ErrorResponse>

    // 코멘트 조회 (아이)
    @GET("comment/{inputOutputId}")
    suspend fun getComment(
        @Path("inputOutputId") inputOutputId: String
    ): NetworkResponse<CommentResponseDto, ErrorResponse>

    // 코멘트 작성 (부모)
    @POST("comment/{childId}/{inputOutputId}")
    suspend fun writeParentComment(
        @Path("childId") childId: String,
        @Path("inputOutputId") inputOutputId: String,
        @Body writeCommentRequest: WriteCommentRequest
    ): NetworkResponse<CommentResponseDto, ErrorResponse>

    // 코멘트 조회 (부모)
    @GET("comment/{childId}/{inputOutputId}")
    suspend fun getParentComment(
        @Path("childId") childId: String,
        @Path("inputOutputId") inputOutputId: String
    ): NetworkResponse<CommentResponseDto, ErrorResponse>


    // 월 칭찬 조회 (yyyy-MM)
    @POST("compliment/month/{inputOutputDate}")
    suspend fun getCompliment(@Path("inputOutputDate") inputOuputDate: String): NetworkResponse<List<ComplimentDto>, ErrorResponse>

    @POST("graph/week")
    suspend fun getWeekGraph(): NetworkResponse<List<WeekGraphResponseDto>, ErrorResponse>

    @POST("graph/category")
    suspend fun getMonthGraph(): NetworkResponse<MonthGraphResponseDto, ErrorResponse>

    @POST("inputoutput/total/out/{inputOutputDate}")
    suspend fun getTotalMonthOutput(@Path("inputOutputDate") inputOutputDate: String): NetworkResponse<TotalMonthOutputResponse, ErrorResponse>

    // 사용자 인증 - 1원 입금
    @POST("accounts/send1Cert")
    suspend fun send1Won(@Body accountNumber: send1WonDto): NetworkResponse<send1WonResponseDto, ErrorResponse>

    // 사용자 인증 - 부모 인증코드 확인
    @POST("accounts/chekcParent1Cert")
    suspend fun checkParentAuth(@Body checkParentAuthDto: checkAuthDto): NetworkResponse<checkParentAuthResponseDto, ErrorResponse>

    // 사용자 인증 - 자녀 인증코드 확인
    @POST("accounts/checkChild1Cert")
    suspend fun checkChildAuth(@Body checkChildAuthDto: checkAuthDto): NetworkResponse<checkChildAuthResponseDto, ErrorResponse>

    @GET("parent/findMyChilds")
    suspend fun getAllUnActivatedChild(): NetworkResponse<List<getAllUnActivatedChildResponseDto>, ErrorResponse>

}