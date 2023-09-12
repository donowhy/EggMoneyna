package com.shbhack.eggmoneyna.ui.common.animation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.shbhack.eggmoneyna.R

@Composable
fun EggAnimationView() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_egg))
    val progress by animateLottieCompositionAsState(
        composition,
        speed = 1.0f,
        iterations = LottieConstants.IterateForever,
//        clipSpec = LottieClipSpec.Progress(0.0f, 0.95f)
    )
    Surface(modifier = Modifier.fillMaxWidth().height(360.dp).padding(start = 24.dp, end = 24.dp),
        color = Color.Transparent) {
        LottieAnimation(composition = composition, progress = progress)
    }
}
