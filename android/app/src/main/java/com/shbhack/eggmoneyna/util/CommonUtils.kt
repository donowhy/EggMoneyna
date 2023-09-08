package com.shbhack.eggmoneyna.util

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

object CommonUtils {
    @Composable
    fun setSystemBarColor(color: Color) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                val window = (view.context as Activity).window
                window.statusBarColor = color.toArgb()
                window.navigationBarColor = color.toArgb()
            }
        }
    }
}