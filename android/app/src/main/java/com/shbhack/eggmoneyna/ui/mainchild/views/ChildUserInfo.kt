package com.shbhack.eggmoneyna.ui.mainchild.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.theme.contextTextColor
import com.shbhack.eggmoneyna.ui.theme.keyColor2
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun ChildUserInfo(name: String, day: String) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = name,
                style = TextStyle(
                    fontSize = 16.ssp,
                    fontWeight = FontWeight.SemiBold,
                    color = keyColor2
                )
            )
            Text(
                text = " 님",
                style = TextStyle(
                    fontSize = 14.ssp,
                    fontWeight = FontWeight.SemiBold,
                    color = contextTextColor
                )
            )
        }
        Spacer(modifier = Modifier.height(5.sdp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(id = R.string.mainChildDDayStart),
                style = TextStyle(
                    fontSize = 14.ssp,
                    fontWeight = FontWeight.SemiBold,
                    color = contextTextColor
                )
            )
            Spacer(modifier = Modifier.width(5.sdp))
            Text(
                text = day,
                style = TextStyle(
                    fontSize = 16.ssp,
                    fontWeight = FontWeight.Bold,
                )
            )
            Spacer(modifier = Modifier.width(2.sdp))
            Text(
                text = stringResource(id = R.string.mainChildDDayEnd),
                style = TextStyle(
                    fontSize = 14.ssp,
                    fontWeight = FontWeight.SemiBold,
                    color = contextTextColor
                )
            )
        }
    }
}