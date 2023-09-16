package com.shbhack.eggmoneyna.ui.mainchild.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.theme.contextTextColor
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun MyComplimentCard(count: Int) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.sdp, vertical = 16.sdp),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .aspectRatio(1f),
                painter = painterResource(id = R.drawable.icon_compliment),
                contentDescription = "나의 칭찬 아이콘"
            )
            Spacer(modifier = Modifier.width(20.sdp))
            Text(
                stringResource(id = R.string.mainChildCompliment),
                style = TextStyle(
                    fontSize = 12.ssp,
                    fontWeight = FontWeight.Medium,
                    color = contextTextColor
                )
            )
        }
        Spacer(modifier = Modifier.height(0.sdp).weight(1f))
        Row {
            Spacer(modifier = Modifier
                .width(0.sdp)
                .weight(1f))
            Text(
                "${count}회",
                style = TextStyle(
                    fontSize = 14.ssp,
                    fontWeight = FontWeight.Bold,
                )
            )
        }
    }
}
