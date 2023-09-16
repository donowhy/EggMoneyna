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
import com.shbhack.eggmoneyna.data.remote.ApiService
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import retrofit2.http.Path
import java.text.SimpleDateFormat
import java.util.Date
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

    override suspend fun getAllUnActivatedChild(): NetworkResponse<List<GetAllUnActivatedChildResponseDto>, ErrorResponse> {
        return apiService.getAllUnActivatedChild()
    }

    override suspend fun activatingChild(childId: Long): NetworkResponse<ActivatingChildResponseDto, ErrorResponse> {
        return apiService.activatingChild(childId)
    }

    override suspend fun myChildInfo(): NetworkResponse<List<MyChildInfoResponseDto>, ErrorResponse> {
        return apiService.myChildInfo()
    }

    override suspend fun childHomeInfo(@Path(value = "inputDate") inputDate: String): NetworkResponse<ChildHomeInfoResponseDto, ErrorResponse> {
        val dateFormat = "yyyy-MM-dd"
        val date = Date(System.currentTimeMillis())
//        return apiService.childHomeInfo(SimpleDateFormat(dateFormat).format(date))
        return apiService.childHomeInfo("2023-09-01")
    }

    override suspend fun getWishBoxInfo(wishAccount: WishBoxInfoDto): NetworkResponse<WishBoxInfoResponseDto, ErrorResponse> {
        return apiService.getWishBoxInfo(wishAccount)
    }

}