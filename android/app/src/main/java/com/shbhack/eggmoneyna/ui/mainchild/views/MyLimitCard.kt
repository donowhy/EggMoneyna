package com.shbhack.eggmoneyna.ui.mainchild.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.common.card.coloredShadow
import com.shbhack.eggmoneyna.ui.theme.contextTextColor
import com.shbhack.eggmoneyna.ui.theme.keyColor2
import com.shbhack.eggmoneyna.ui.theme.secondaryColor1
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun MyLimitCard(
    limit: String,
    last: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .coloredShadow(color = Color.Black.copy(0.05f), 10.sdp, 8.sdp, 0.sdp, 0.sdp, 0.5f)
            .background(color = Color.White, shape = RoundedCornerShape(10.sdp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.sdp, vertical = 10.sdp),
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(0.13f)
                        .aspectRatio(1f),
                    painter = painterResource(id = R.drawable.icon_limit),
                    contentDescription = "한도 아이콘"
                )
                Spacer(modifier = Modifier.width(20.sdp))
                Column {
                    Row (verticalAlignment = Alignment.Bottom) {
                        Text(
                            stringResource(id = R.string.mainChildMaxmumStart),
                            style = TextStyle(
                                fontSize = 12.ssp,
                                fontWeight = FontWeight.Normal,
                                color = contextTextColor
                            )
                        )
                        Text(
                            limit,
                            style = TextStyle(
                                fontSize = 14.ssp,
                                fontWeight = FontWeight.SemiBold,
                                color = secondaryColor1
                            ),
                            modifier = Modifier.padding(horizontal = 3.sdp)
                        )
                        Text(
                            stringResource(id = R.string.mainChildMaxmumMiddle),
                            style = TextStyle(
                                fontSize = 12.ssp,
                                fontWeight = FontWeight.Normal,
                                color = contextTextColor
                            )
                        )
                    }
                    Row (verticalAlignment = Alignment.Bottom) {
                        Text(
                            last,
                            style = TextStyle(
                                fontSize = 14.ssp,
                                fontWeight = FontWeight.SemiBold,
                                color = keyColor2
                            ),
                            modifier = Modifier.padding(end = 3.sdp)
                        )
                        Text(
                            stringResource(id = R.string.mainChildMaxmumEnd),
                            style = TextStyle(
                                fontSize = 12.ssp,
                                fontWeight = FontWeight.Normal,
                                color = contextTextColor
                            )
                        )
                    }
                }
            }
        }
    }
}