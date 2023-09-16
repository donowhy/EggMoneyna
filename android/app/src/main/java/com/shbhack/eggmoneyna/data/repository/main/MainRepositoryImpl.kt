package com.shbhack.eggmoneyna.data.repository.main

import com.shbhack.eggmoneyna.data.model.ComplimentDto
import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.InputOutputsResponse
import com.shbhack.eggmoneyna.data.remote.ApiService
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MainRepository {
    override suspend fun getInputOutput(inputOuputDate: String): NetworkResponse<InputOutputsResponse, ErrorResponse> {
        return apiService.getInputOutput(inputOuputDate)
    }

    override suspend fun getCompliment(inputOuputDate: String): NetworkResponse<List<ComplimentDto>, ErrorResponse> {
        return apiService.getCompliment(inputOuputDate)
    }

}