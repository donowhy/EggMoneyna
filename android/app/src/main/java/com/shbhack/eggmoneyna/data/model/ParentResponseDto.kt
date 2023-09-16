package com.shbhack.eggmoneyna.data.model

data class getAllUnActivatedChildResponseDto(
    var id: Long,
    var childName: String,
    var age: Int,
    var gender: Boolean
)