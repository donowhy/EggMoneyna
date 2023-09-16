package com.shbhack.eggmoneyna.data.model

data class ParentSaveResponseDto(
    val id : Int,
    val parentId: String,
    val parentToken: String,
    val role: String
)