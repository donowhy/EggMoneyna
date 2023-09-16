package com.shbhack.eggmoneyna.data.remote

import com.shbhack.eggmoneyna.data.model.ActivatingChildResponseDto
import com.shbhack.eggmoneyna.data.model.ChildHomeInfoResponseDto
import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.InputOutputsResponse
import com.shbhack.eggmoneyna.data.model.MonsterResponseDto
import com.shbhack.eggmoneyna.data.model.MonsterSaveResponseDto
import com.shbhack.eggmoneyna.data.model.ParentSaveDto
import com.shbhack.eggmoneyna.data.model.ParentSaveResponseDto
import com.shbhack.eggmoneyna.data.model.RelationResponse
import com.shbhack.eggmoneyna.data.model.WriteCommentRequest
import com.shbhack.eggmoneyna.data.model.checkAuthDto
import com.shbhack.eggmoneyna.data.model.checkChildAuthResponseDto
import com.shbhack.eggmoneyna.data.model.checkParentAuthResponseDto
import com.shbhack.eggmoneyna.data.model.GetAllUnActivatedChildResponseDto
import com.shbhack.eggmoneyna.data.model.MyChildInfoResponseDto
import com.shbhack.eggmoneyna.data.model.WishBoxInfoDto
import com.shbhack.eggmoneyna.data.model.WishBoxInfoResponseDto
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
    @POST("/monsters/save")
    suspend fun save(): NetworkResponse<MonsterSaveResponseDto, ErrorResponse>

    @GET("/monsters/getMyMong")
    suspend fun getMyMong(): NetworkResponse<MonsterResponseDto, ErrorResponse>


    // 당일 입출금 조회
    @POST("inputoutput/all/{inputOuputDate}")
    suspend fun getInputOutput(@Path("inputOuputDate") inputOuputDate: String): NetworkResponse<InputOutputsResponse, ErrorResponse>

    // 코멘트
    @POST("/comment/{inputOutputId}")
    suspend fun writeComment(
        @Path("inputOutputDate") inputOutputDate: String,
        @Body writeCommentRequest: WriteCommentRequest
    ): NetworkResponse<MonsterResponseDto, ErrorResponse>

    // 사용자 인증 - 1원 입금
    @POST("accounts/send1Cert")
    suspend fun send1Won(@Body accountNumber: send1WonDto): NetworkResponse<send1WonResponseDto, ErrorResponse>

    // 사용자 인증 - 부모 인증코드 확인
    @POST("accounts/chekcParent1Cert")
    suspend fun checkParentAuth(@Body checkParentAuthDto: checkAuthDto): NetworkResponse<checkParentAuthResponseDto, ErrorResponse>

    // 사용자 인증 - 자녀 인증코드 확인
    @POST("accounts/checkChild1Cert")
    suspend fun checkChildAuth(@Body checkChildAuthDto: checkAuthDto): NetworkResponse<checkChildAuthResponseDto, ErrorResponse>

    // 나의 자녀선택 리스트 (비활성화 된 자녀들)
    @GET("parent/findMyChilds")
    suspend fun getAllUnActivatedChild() : NetworkResponse<List<GetAllUnActivatedChildResponseDto>, ErrorResponse>

    // 자녀 계정 활성화
    @POST("relations/createEggMoney/{childId}")
    suspend fun activatingChild(@Path("childId") childId: Long) : NetworkResponse<ActivatingChildResponseDto, ErrorResponse>

    // 나의 등록된 리스트 (활성화 된 자녀들)
    @GET("parent/findMyEggMoneyChilds")
    suspend fun myChildInfo() : NetworkResponse<List<MyChildInfoResponseDto>, ErrorResponse>

    // 자녀 홈 정보
    @GET("child/getMyHome/{inputDate}")
    suspend fun childHomeInfo(@Path("inputDate") inputDate: String) : NetworkResponse<ChildHomeInfoResponseDto, ErrorResponse>

    // 위시박스 정보
    @GET("wishbox/getOneWishbox")
    suspend fun getWishBoxInfo(@Body wishAccount: WishBoxInfoDto) : NetworkResponse<WishBoxInfoResponseDto, ErrorResponse>

    // 위시박스 생성
//    @POST("wishbox/create")
//    suspend fun createWishBox(@Body wishInfo: )
}