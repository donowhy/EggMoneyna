package com.shbhack.eggmoneyna.data.repository.expense

import com.shbhack.eggmoneyna.data.model.ComplimentDto
import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.InputOutputsResponse
import com.shbhack.eggmoneyna.data.model.MonthGraphResponseDto
import com.shbhack.eggmoneyna.data.model.WeekGraphResponseDto
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import retrofit2.http.Path

interface ExpenseRepository {
    // 주간 그래프
    suspend fun getWeekGraph(): NetworkResponse<List<WeekGraphResponseDto>, ErrorResponse>

    // 월간 그래프
    suspend fun getMonthGraph(): NetworkResponse<MonthGraphResponseDto, ErrorResponse>
}