package com.shbhack.eggmoneyna.ui.common.banner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun ExplainBanner(color: Color, title: String, content: String, imgId: Int, description: String) {
    Box(
        modifier = Modifier
            .background(color)
            .fillMaxWidth()
            .padding(top = 28.sdp, start = 18.sdp, end = 18.sdp, bottom = 8.sdp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.7f)
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 20.ssp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(15.sdp))
            Text(
                text = content, style = TextStyle(
                    fontSize = 12.ssp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(40.sdp))
        }

        Image(
            modifier = Modifier
                .fillMaxWidth(0.32f)
                .aspectRatio(1f)
                .align(alignment = Alignment.BottomEnd),
            painter = painterResource(id = imgId),
            contentDescription = description
        )
    }
}