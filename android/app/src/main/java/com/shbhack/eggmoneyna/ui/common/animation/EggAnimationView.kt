package com.shbhack.eggmoneyna.ui.common.animation

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.shbhack.eggmoneyna.R

@Composable
fun EggAnimationView(gif: Int) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_egg))
    val progress by animateLottieCompositionAsState(
        composition,
        speed = 1.0f,
        iterations = LottieConstants.IterateForever,
//        clipSpec = LottieClipSpec.Progress(0.0f, 0.95f)
    )
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)
            .padding(start = 24.dp, end = 24.dp),
        color = Color.Transparent
    ) {
        if (gif == 0) {
            LottieAnimation(composition = composition, progress = progress)
        } else {
            Image(
                painter = rememberAsyncImagePainter(gif, imageLoader),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

    }
}
