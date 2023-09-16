package com.shbhack.eggmoneyna.data.repository.main

import com.shbhack.eggmoneyna.data.model.ActivatingChildResponseDto
import com.shbhack.eggmoneyna.data.model.ChildHomeInfoResponseDto
import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.InputOutputsResponse
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
import retrofit2.http.Path

interface MainRepository {
    // 당일 입출금 조회
    suspend fun getInputOutput(@Path("inputOuputDate") inputOuputDate: String): NetworkResponse<InputOutputsResponse, ErrorResponse>

    // 사용자 인증 - 1원 입금
    suspend fun send1Won(@Body accountNumber: send1WonDto): NetworkResponse<send1WonResponseDto, ErrorResponse>

    // 사용자 인증 - 부모 인증코드 확인
    suspend fun checkParentAuth(@Body checkParentAuthDto: checkAuthDto): NetworkResponse<checkParentAuthResponseDto, ErrorResponse>

    // 사용자 인증 - 자녀 인증코드 확인
    suspend fun checkChildAuth(@Body checkChildAuthDto: checkAuthDto): NetworkResponse<checkChildAuthResponseDto, ErrorResponse>

    // 나의 자녀선택 리스트 (비활성화 된 자녀들)
    suspend fun getAllUnActivatedChild() : NetworkResponse<List<GetAllUnActivatedChildResponseDto>, ErrorResponse>

    // 자녀 계정 활성화
    suspend fun activatingChild(@Path("childId") childId: Long) : NetworkResponse<ActivatingChildResponseDto, ErrorResponse>

    // 나의 등록된 자녀리스트 (활성화 된 자녀들)
    suspend fun myChildInfo() : NetworkResponse<List<MyChildInfoResponseDto>, ErrorResponse>

    // 아이 정보 홈
    suspend fun childHomeInfo(@Path("inputDate") inputDate: String) : NetworkResponse<ChildHomeInfoResponseDto, ErrorResponse>

    // 위시박스 정보
    suspend fun getWishBoxInfo(@Body wishAccount: WishBoxInfoDto) : NetworkResponse<WishBoxInfoResponseDto, ErrorResponse>
}