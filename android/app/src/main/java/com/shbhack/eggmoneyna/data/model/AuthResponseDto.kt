package com.shbhack.eggmoneyna.data.model

data class send1WonResponseDto(
    var certNumber: Int
)

data class checkParentAuthResponseDto(
    var isRight : Boolean,
    var parentToken: String
)

data class checkChildAuthResponseDto(
    var isRight: Boolean,
    var isAccountActivate : Boolean,
    var childToken: String
)