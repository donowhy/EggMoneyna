package com.shbhack.eggmoneyna.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun ColorBackgroundWithText(color: Color, title: String, content: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = color)
            .padding(26.sdp)
    ) {
        Text(
            text = title, fontWeight = FontWeight.Medium, fontSize = 16.ssp, color = Color.White
        )
        Spacer(
            modifier = Modifier.size(4.sdp)
        )
        Text(
            text = content, fontWeight = FontWeight.SemiBold,
            fontSize = 20.ssp, color = Color.White
        )
    }
}