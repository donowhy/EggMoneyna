package com.shbhack.eggmoneyna.ui.iconpack

import androidx.compose.ui.graphics.vector.ImageVector
import com.shbhack.eggmoneyna.ui.iconpack.myiconpack.Camera
import com.shbhack.eggmoneyna.ui.iconpack.myiconpack.Search
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons = listOf(Camera, Search)
    return __AllIcons!!
  }
