package com.shbhack.eggmoneyna.data.repository.comment

import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.MonsterResponseDto
import com.shbhack.eggmoneyna.data.model.WriteCommentRequest
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.Path

interface CommentRepository {
    // 부모 생성 및 계좌 생성
    suspend fun writeComment(
        @Path("inputOutputDate") inputOutputDate: String,
        @Body writeCommentRequest: WriteCommentRequest
    ): NetworkResponse<MonsterResponseDto, ErrorResponse>

}