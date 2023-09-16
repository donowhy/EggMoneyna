package com.shbhack.eggmoneyna.data.repository.main

import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.InputOutputsResponse
import com.shbhack.eggmoneyna.data.model.checkAuthDto
import com.shbhack.eggmoneyna.data.model.checkChildAuthResponseDto
import com.shbhack.eggmoneyna.data.model.checkParentAuthResponseDto
import com.shbhack.eggmoneyna.data.model.getAllUnActivatedChildResponseDto
import com.shbhack.eggmoneyna.data.model.send1WonDto
import com.shbhack.eggmoneyna.data.model.send1WonResponseDto
import com.shbhack.eggmoneyna.data.remote.ApiService
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MainRepository {
    override suspend fun getInputOutput(inputOuputDate: String): NetworkResponse<InputOutputsResponse, ErrorResponse> {
        return apiService.getInputOutput(inputOuputDate)
    }

    override suspend fun send1Won(accountNumber: send1WonDto): NetworkResponse<send1WonResponseDto, ErrorResponse> {
        return apiService.send1Won(accountNumber)
    }

    override suspend fun checkParentAuth(checkParentAuthDto: checkAuthDto): NetworkResponse<checkParentAuthResponseDto, ErrorResponse> {
        return apiService.checkParentAuth(checkParentAuthDto)
    }

    override suspend fun checkChildAuth(checkChildAuthDto: checkAuthDto): NetworkResponse<checkChildAuthResponseDto, ErrorResponse> {
        return apiService.checkChildAuth(checkChildAuthDto)
    }

    override suspend fun getAllUnActivatedChild(): NetworkResponse<List<getAllUnActivatedChildResponseDto>, ErrorResponse> {
        return apiService.getAllUnActivatedChild()
    }

}