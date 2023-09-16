package com.shbhack.eggmoneyna.ui.mainchild.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.common.util.noRippleClickable
import ir.kaaveh.sdpcompose.sdp

@Composable
fun TopIconRow(alarmOnClick: () -> Unit, settingOnclick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .width(0.sdp)
                .weight(1f)
        )
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