package com.shbhack.eggmoneyna.ui.common.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.common.util.noRippleClickable
import com.shbhack.eggmoneyna.ui.theme.logoColor
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun ChildMoneyInfoCard(
    name: String,
    lastMoney: String,
    moneyOnClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 15.sdp)
            .coloredShadow(color = Color.Black.copy(0.05f), 10.sdp, 8.sdp, 0.sdp, 0.sdp, 0.5f)
            .background(color = Color.White, shape = RoundedCornerShape(10.sdp))
            .noRippleClickable {
                moneyOnClick()
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.sdp),
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .aspectRatio(1f),
                    painter = painterResource(id = R.drawable.shinhan_logo_blue),
                    contentDescription = "신한은행 로고"
                )
                Spacer(modifier = Modifier.width(20.sdp))
                Column {
                    Row {
                        Text(
                            name,
                            style = TextStyle(
                                fontSize = 13.ssp,
                                fontWeight = FontWeight.Medium,
                                color = logoColor
                            )
                        )
                        Text(
                            "의 잔액",
                            style = TextStyle(
                                fontSize = 13.ssp,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(5.sdp))
                    Text(
                        lastMoney,
                        style = TextStyle(
                            fontSize = 20.ssp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    )
                }
            }
        }
    }
}