package com.shbhack.eggmoneyna.ui.wishbox.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.common.lottie.LottieLoader
import com.shbhack.eggmoneyna.ui.theme.keyColor2
import com.shbhack.eggmoneyna.ui.theme.keyColorLight1
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun WishBoxExplainBox() {
    Box(
        modifier = Modifier
            .background(color = keyColorLight1, shape = RoundedCornerShape(10.sdp))
            .fillMaxWidth()
            .padding(top = 30.sdp, start = 20.sdp, end = 8.sdp, bottom = 8.sdp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.7f)
        ) {
            Text(
                text = stringResource(id = R.string.wishBoxBannerTitle),
                style = TextStyle(
                    fontSize = 18.ssp,
                    fontWeight = FontWeight.Black,
                    color = keyColor2
                )
            )
            Spacer(modifier = Modifier.height(30.sdp))
            Text(
                text = stringResource(id = R.string.wishBoxBannerContent),
                style = TextStyle(
                    fontSize = 12.ssp,
                    fontWeight = FontWeight.Normal,
                )
            )
            Spacer(modifier = Modifier.height(40.sdp))
        }

        LottieLoader(
            source = R.raw.wishbox_recommend,
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .aspectRatio(1f)
                .align(alignment = Alignment.BottomEnd)
        )
    }
}