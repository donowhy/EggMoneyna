package com.shbhack.eggmoneyna.data.model

data class InputOutputsResponse(
    val inputOutputs: List<InputOutputDTO>
)

data class InputOutputDTO(
    val createTime: String,
    val updateTime: String,
    val id: Int,
    val brandName: String?,
    val brandImg: String,
    val bigCategory: String,
    val smallCategory: String,
    val input: Int,
    val output: Int
)