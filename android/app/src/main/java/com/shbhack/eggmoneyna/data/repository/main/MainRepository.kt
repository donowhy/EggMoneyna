package com.shbhack.eggmoneyna.data.repository.main

import com.shbhack.eggmoneyna.data.model.BalanceResponse
import com.shbhack.eggmoneyna.data.model.ComplimentDto
import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.InputOutputsResponse
import com.shbhack.eggmoneyna.data.model.checkAuthDto
import com.shbhack.eggmoneyna.data.model.checkChildAuthResponseDto
import com.shbhack.eggmoneyna.data.model.checkParentAuthResponseDto
import com.shbhack.eggmoneyna.data.model.getAllUnActivatedChildResponseDto
import com.shbhack.eggmoneyna.data.model.send1WonDto
import com.shbhack.eggmoneyna.data.model.send1WonResponseDto
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.Path

interface MainRepository {
    // 당일 입출금 조회
    suspend fun getInputOutput(@Path("inputOutputDate") inputOutputDate: String): NetworkResponse<InputOutputsResponse, ErrorResponse>

    // 월 칭찬 여부
    suspend fun getCompliment(@Path("inputOutputDate") inputOutputDate: String): NetworkResponse<List<ComplimentDto>, ErrorResponse>

    // 사용자 인증 - 1원 입금
    suspend fun send1Won(@Body accountNumber: send1WonDto): NetworkResponse<send1WonResponseDto, ErrorResponse>

    // 사용자 인증 - 부모 인증코드 확인
    suspend fun checkParentAuth(@Body checkParentAuthDto: checkAuthDto): NetworkResponse<checkParentAuthResponseDto, ErrorResponse>

    // 사용자 인증 - 자녀 인증코드 확인
    suspend fun checkChildAuth(@Body checkChildAuthDto: checkAuthDto): NetworkResponse<checkChildAuthResponseDto, ErrorResponse>

    suspend fun getAllUnActivatedChild(): NetworkResponse<List<getAllUnActivatedChildResponseDto>, ErrorResponse>

    // 내 계좌 잔액 조회
    suspend fun getMyBalance(): NetworkResponse<BalanceResponse, ErrorResponse>
}