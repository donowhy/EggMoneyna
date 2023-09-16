package com.shbhack.eggmoneyna.data.model

data class CommentResponseDto(
    val id: Int,
    val childNickname: String,
    val childComment: String,
    val childCommentCreateTime: String,
    val parentNickname: String,
    val parentComment: String,
    val parentCommentCreateTime: String,
    val compliment: Boolean
)