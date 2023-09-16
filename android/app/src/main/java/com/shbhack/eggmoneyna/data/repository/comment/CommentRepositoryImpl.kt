package com.shbhack.eggmoneyna.data.repository.comment

import com.shbhack.eggmoneyna.data.model.ErrorResponse
import com.shbhack.eggmoneyna.data.model.MonsterResponseDto
import com.shbhack.eggmoneyna.data.model.ParentSaveDto
import com.shbhack.eggmoneyna.data.model.ParentSaveResponseDto
import com.shbhack.eggmoneyna.data.model.RelationResponse
import com.shbhack.eggmoneyna.data.model.WriteCommentRequest
import com.shbhack.eggmoneyna.data.remote.ApiService
import com.shbhack.eggmoneyna.util.network.NetworkResponse
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CommentRepository {
    override suspend fun writeComment(
        inputOutputDate: String,
        writeCommentRequest: WriteCommentRequest
    ): NetworkResponse<MonsterResponseDto, ErrorResponse> {
        return apiService.writeComment(inputOutputDate, writeCommentRequest)
    }
}