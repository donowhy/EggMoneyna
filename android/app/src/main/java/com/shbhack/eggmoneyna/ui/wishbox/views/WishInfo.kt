package com.shbhack.eggmoneyna.ui.wishbox.views

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.shbhack.eggmoneyna.ui.theme.contextTextColor
import com.shbhack.eggmoneyna.ui.theme.secondaryColor2
import com.shbhack.eggmoneyna.util.MoneyUtils
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun WishInfo(goods: String, lastMoney: Int) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = goods,
                style = TextStyle(
                    fontSize = 20.ssp,
                    fontWeight = FontWeight.Bold,
                    color = secondaryColor2
                )
            )
            Text(
                text = " 까지",
                style = TextStyle(
                    fontSize = 16.ssp,
                    fontWeight = FontWeight.SemiBold,
                    color = contextTextColor
                )
            )
        }
        Spacer(modifier = Modifier.height(5.sdp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "남은 금액",
                style = TextStyle(
                    fontSize = 16.ssp,
                    fontWeight = FontWeight.SemiBold,
                    color = contextTextColor
                )
            )
            Spacer(modifier = Modifier.width(10.sdp))
            Text(
                text = MoneyUtils.convertAddComma(lastMoney),
                style = TextStyle(
                    fontSize = 20.ssp,
                    fontWeight = FontWeight.Bold,
                )
            )
            Spacer(modifier = Modifier.width(5.sdp))
            Text(
                text = "원",
                style = TextStyle(
                    fontSize = 16.ssp,
                    fontWeight = FontWeight.SemiBold,
                    color = contextTextColor
                )
            )
        }
    }
}