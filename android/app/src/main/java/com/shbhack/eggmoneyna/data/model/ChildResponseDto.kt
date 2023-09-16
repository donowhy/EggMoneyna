package com.shbhack.eggmoneyna.data.model

data class ChildHomeInfoResponseDto(
    var childName: String = "김은하",
    var shinhanMongDate: String = "2023-09-13",
    var balance: Int = 500000,
    var limitMoney: Int = 100000,
    var leftMoneyToLimit: Int = 56000,
    var attemptDate: String = "13/30",
    var compliment: Int = 10,
    var wishboxNumber: Long = 0,
    var haveWishbox: Boolean = false
)