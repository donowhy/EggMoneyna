package com.shbhack.eggmoneyna.util

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.shbhack.eggmoneyna.R

object CommonUtils {
    @Composable
    fun buttonRadius40(text : String, color: Color, onClick: () -> Unit) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = color, shape = RoundedCornerShape(40.dp)),
            colors = ButtonDefaults.buttonColors(color, Color.White)
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
    }

    @Composable
    fun LottieLoader(source: Int, modifier: Modifier) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(source))
        LottieAnimation(
            modifier = modifier,
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
    }

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