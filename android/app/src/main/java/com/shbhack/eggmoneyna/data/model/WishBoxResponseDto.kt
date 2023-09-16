package com.shbhack.eggmoneyna.data.model

data class WishBoxInfoResponseDto(
    var wishName: String = "",
    var price: Int = 0,
    var balance: Int = 0,
    var virtualNumber: Long = 0,
    var createTime: String = ""
)