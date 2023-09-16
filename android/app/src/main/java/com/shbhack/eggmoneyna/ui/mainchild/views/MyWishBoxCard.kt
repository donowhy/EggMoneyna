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
import com.shbhack.eggmoneyna.ui.common.util.noRippleClickable
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun MyWishBoxCard(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .coloredShadow(color = Color.Black.copy(0.05f), 10.sdp, 8.sdp, 0.sdp, 0.sdp, 0.5f)
            .background(color = Color.White, shape = RoundedCornerShape(10.sdp))
            .noRippleClickable {
                onClick()
            }
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
                    painter = painterResource(id = R.drawable.icon_box),
                    contentDescription = "나의 위시박스 아이콘"
                )
                Spacer(modifier = Modifier.width(20.sdp))
                Text(
                    stringResource(id = R.string.mainChildWishBox),
                    style = TextStyle(
                        fontSize = 13.ssp,
                        fontWeight = FontWeight.SemiBold,
                    )
                )
            }
        }
    }
}