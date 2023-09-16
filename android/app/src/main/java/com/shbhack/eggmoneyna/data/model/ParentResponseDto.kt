package com.shbhack.eggmoneyna.data.model

data class GetAllUnActivatedChildResponseDto(
    var id: Long,
    var childName: String,
    var age: Int,
    var gender: Boolean
)

data class MyChildInfoResponseDto(
    var childId: Long,
    var childName: String,
    var balance: Int
)

data class ActivatingChildResponseDto(
    var parentName: String,
    var childToken: String,
    var childName: String,
    var pid: Long,
    var cid: Long
)