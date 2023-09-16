package com.shbhack.eggmoneyna.data.model

data class MonsterSaveResponseDto(
    val success: String,
    val message: String,
    val data: MonsterSaveDto
)

data class MonsterSaveDto(
    val name: String,
    val feel: String,
    val benefit: String
)
