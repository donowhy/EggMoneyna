package com.shbhack.eggmoneyna.data.repository.expense

import com.shbhack.eggmoneyna.data.model.ComplimentDto
import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.InputOutputsResponse
import com.shbhack.eggmoneyna.data.model.MonthGraphResponseDto
import com.shbhack.eggmoneyna.data.model.WeekGraphResponseDto
import com.shbhack.eggmoneyna.data.remote.ApiService
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ExpenseRepository {
    override suspend fun getWeekGraph(): NetworkResponse<List<WeekGraphResponseDto>, ErrorResponse> {
        return apiService.getWeekGraph()
    }

    override suspend fun getMonthGraph(): NetworkResponse<MonthGraphResponseDto, ErrorResponse> {
        return apiService.getMonthGraph()
    }

}