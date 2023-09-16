package com.shbhack.eggmoneyna.data.model

data class MonsterResponseDto(
    val success: String,
    val message: String,
    val data: MonsterDto
)

data class MonsterDto(
    val name: String,
    val status: String,
    val feel: String,
    val exp: Int,
    val benefit: String
)
