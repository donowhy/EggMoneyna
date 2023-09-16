package com.shbhack.eggmoneyna.ui.mainparent.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.shbhack.eggmoneyna.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun WarningBottom() {
    Column {
        Spacer(modifier = Modifier.height(15.sdp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.sdp)
            ) {
                Text(
                    text = stringResource(id = R.string.mainParentWarningTitle),
                    style = TextStyle(
                        fontSize = 12.ssp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Spacer(modifier = Modifier.height(10.sdp))
                Text(
                    text = stringResource(id = R.string.mainParentWarningContent1),
                    style = TextStyle(
                        fontSize = 10.ssp,
                        fontWeight = FontWeight.Normal,
                    )
                )
                Spacer(modifier = Modifier.height(5.sdp))
                Text(
                    text = stringResource(id = R.string.mainParentWarningContent2),
                    style = TextStyle(
                        fontSize = 10.ssp,
                        fontWeight = FontWeight.Normal,
                    )
                )
            }
        }
    }
}