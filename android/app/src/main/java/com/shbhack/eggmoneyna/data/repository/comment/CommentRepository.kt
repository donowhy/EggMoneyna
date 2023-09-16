package com.shbhack.eggmoneyna.data.repository.comment

import com.shbhack.eggmoneyna.data.model.CommentResponseDto
import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.MonsterResponseDto
import com.shbhack.eggmoneyna.data.model.WriteCommentRequest
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentRepository {
    suspend fun writeComment(
        @Path("inputOutputId") inputOutputId: String,
        @Body writeCommentRequest: WriteCommentRequest
    ): NetworkResponse<CommentResponseDto, ErrorResponse>

    suspend fun getComment(
        @Path("inputOutputId") inputOutputId: String
    ): NetworkResponse<CommentResponseDto, ErrorResponse>

    // 코멘트 작성 (부모)
    suspend fun writeParentComment(
        @Path("childId") childId: String,
        @Path("inputOutputId") inputOutputId: String,
        @Body writeCommentRequest: WriteCommentRequest
    ): NetworkResponse<CommentResponseDto, ErrorResponse>

    // 코멘트 조회 (부모)
    suspend fun getParentComment(
        @Path("childId") childId: String,
        @Path("inputOutputId") inputOutputId: String
    ): NetworkResponse<CommentResponseDto, ErrorResponse>

}