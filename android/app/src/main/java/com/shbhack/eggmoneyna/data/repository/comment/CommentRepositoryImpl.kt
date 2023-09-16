package com.shbhack.eggmoneyna.data.repository.comment

import com.shbhack.eggmoneyna.data.model.CommentResponseDto
import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.WriteCommentRequest
import com.shbhack.eggmoneyna.data.remote.ApiService
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CommentRepository {
    override suspend fun writeComment(
        inputOutputId: String,
        writeCommentRequest: WriteCommentRequest
    ): NetworkResponse<CommentResponseDto, ErrorResponse> {
        return apiService.writeComment(inputOutputId, writeCommentRequest)
    }

    override suspend fun getComment(inputOutputId: String): NetworkResponse<CommentResponseDto, ErrorResponse> {
        return apiService.getComment(inputOutputId)
    }

    override suspend fun writeParentComment(
        childId: String,
        inputOutputId: String,
        writeCommentRequest: WriteCommentRequest
    ): NetworkResponse<CommentResponseDto, ErrorResponse> {
        return apiService.writeParentComment(childId, inputOutputId, writeCommentRequest)
    }

    override suspend fun getParentComment(
        childId: String,
        inputOutputId: String
    ): NetworkResponse<CommentResponseDto, ErrorResponse> {
        return apiService.getParentComment(childId, inputOutputId)
    }


}