package com.shbhack.eggmoneyna.data.model

data class send1WonDto(
    var accountNumber: Long
)

data class checkAuthDto(
    var accountNumber: Long,
    var certNumber: Int
)