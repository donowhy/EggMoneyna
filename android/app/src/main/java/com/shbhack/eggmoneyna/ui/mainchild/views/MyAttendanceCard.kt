package com.shbhack.eggmoneyna.ui.mainchild.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.common.util.noRippleClickable
import com.shbhack.eggmoneyna.ui.theme.contextTextColor
import com.shbhack.eggmoneyna.ui.theme.keyColorLight2
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun MyAttendanceCard(date: Int, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.sdp, vertical = 10.sdp),
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
                painter = painterResource(id = R.drawable.icon_attendance),
                contentDescription = "나의 출석 아이콘"
            )
            Spacer(modifier = Modifier.width(20.sdp))
            Text(
                stringResource(id = R.string.mainChildAttendance),
                style = TextStyle(
                    fontSize = 12.ssp,
                    fontWeight = FontWeight.SemiBold,
                    color = contextTextColor
                )
            )
        }
        Spacer(modifier = Modifier.height(0.sdp).weight(1f))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .background(color = keyColorLight2, shape = RoundedCornerShape(10.sdp))
                    .noRippleClickable {
                        onClick()
                    }
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 10.sdp, vertical = 5.sdp),
                    text = "출석",
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.ssp
                )
            }
            Spacer(modifier = Modifier
                .width(0.sdp)
                .weight(1f))
            Text(
                "${date}/30",
                style = TextStyle(
                    fontSize = 14.ssp,
                    fontWeight = FontWeight.Bold,
                )
            )
        }
    }
}
