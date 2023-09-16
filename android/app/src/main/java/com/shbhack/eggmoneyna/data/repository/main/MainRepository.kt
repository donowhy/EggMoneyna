package com.shbhack.eggmoneyna.data.repository.main

import com.shbhack.eggmoneyna.data.model.ComplimentDto
import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.InputOutputsResponse
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import retrofit2.http.Path

interface MainRepository {
    // 당일 입출금 조회
    suspend fun getInputOutput(@Path("inputOuputDate") inputOuputDate: String): NetworkResponse<InputOutputsResponse, ErrorResponse>

    // 월 칭찬 여부
    suspend fun getCompliment(@Path("inputOutputDate") inputOuputDate: String): NetworkResponse<List<ComplimentDto>, ErrorResponse>
}