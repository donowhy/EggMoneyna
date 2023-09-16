package com.shbhack.eggmoneyna.data.model

data class RelationResponse(
    val parentId: String,
    val childToken: String,
    val childId: String,
    val cid: Int,
    val pid: Int
)