package com.shbhack.eggmoneyna.ui.mainparent.view

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
import com.shbhack.eggmoneyna.ui.common.util.noRippleClickable
import com.shbhack.eggmoneyna.ui.theme.logoColor
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun MainParentTop(alarmOnClick: () -> Unit, settingOnclick: () -> Unit, addOnClick: () -> Unit) {
    Column {
        Row(
            modifier = Modifier.padding(15.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.mainParentTitle),
                style = TextStyle(fontSize = 20.ssp, fontWeight = FontWeight.Bold)
            )
            Spacer(
                modifier = Modifier
                    .width(0.sdp)
                    .weight(1f)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(0.08f)
                        .aspectRatio(1f)
                        .noRippleClickable {
                            alarmOnClick()
                        },
                    painter = painterResource(id = R.drawable.icon_alarm),
                    contentDescription = "알림 아이콘"
                )
                Spacer(modifier = Modifier.width(10.sdp))
                Image(
                    modifier = Modifier
                        .fillMaxWidth(0.08f)
                        .aspectRatio(1f)
                        .noRippleClickable {
                            settingOnclick()
                        },
                    painter = painterResource(id = R.drawable.icon_setting),
                    contentDescription = "세팅 아이콘"
                )
            }
        }

        Row {
            Spacer(
                modifier = Modifier
                    .width(0.sdp)
                    .weight(1f)
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 15.sdp)
                    .background(color = logoColor, shape = RoundedCornerShape(5.sdp))
                    .noRippleClickable {
                        addOnClick()
                    }
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 10.sdp, vertical = 5.sdp),
                    text = stringResource(id = R.string.mainParentButtonAdd),
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.ssp,
                    color = Color.White
                )
            }
        }
    }
}