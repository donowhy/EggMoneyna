package com.shbhack.eggmoneyna.data.model

data class MonsterResponseDto(
    val exp: Int,
    val status: String,
    val feel: String,
    val historyList: List<HistoryItem>
)

data class HistoryItem(
    val createTime: String,
    val updateTime: String,
    val id: Int,
    val content: String,
    val exp: Int,
    val prefix: Int,
    val monster: MonsterDetail
)

data class MonsterDetail(
    val createTime: String,
    val updateTime: String,
    val id: Int,
    val name: String,
    val status: String,
    val feel: String,
    val exp: Int,
    val benefit: String
)

