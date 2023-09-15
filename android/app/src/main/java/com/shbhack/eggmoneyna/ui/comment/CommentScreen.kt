package com.shbhack.eggmoneyna.ui.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack
import com.shbhack.eggmoneyna.ui.theme.bannerPink
import com.shbhack.eggmoneyna.ui.theme.keyColor1
import com.shbhack.eggmoneyna.ui.theme.keyColorLight1
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun CommentScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopWithBack(
                navController = navController,
                title = stringResource(id = R.string.comment_appbar_title)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            CommentBanner(
                title = "스타벅스 인동점",
                content = "-6,700원",
                "https://picsum.photos/200"
            ) {
                // 칭찬하기 클릭
            }



            Divider(
                modifier = Modifier.padding(top = 12.sdp),
                thickness = 12.sdp,
                color = keyColorLight1
            )



            Text(
                modifier = Modifier.padding(12.sdp),
                text = "이 주의 그래프",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.Black
                )

            )
        }
    }
}

@Composable
fun CommentBanner(
    title: String,
    content: String,
    img: String?,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = bannerPink)
            .padding(start = 32.dp, end = 12.dp, bottom = 32.dp, top = 12.dp)
    ) {
        Surface(
            modifier = Modifier
                .align(Alignment.End)
                .clickable {
                    onClick()
                },
            color = keyColor1,
            shape = RoundedCornerShape(40.dp),
        ) {
            Text(
                modifier = Modifier.padding(
                    start = 10.sdp,
                    end = 10.sdp,
                    top = 4.sdp,
                    bottom = 4.sdp
                ),
                text = "칭찬하기",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 10.ssp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = title, fontWeight = FontWeight.Medium, fontSize = 18.sp, color = Color.White
            )
            Spacer(modifier = Modifier.size(4.sdp))
            AsyncImage(
                model = img,
                contentDescription = "지출 브랜드 로고",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(20.sdp)
                    .clip(CircleShape)
            )
        }

        Spacer(
            modifier = Modifier.size(4.dp)
        )
        Text(
            text = content, fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp, color = Color.White
        )
    }
}

@Composable
fun CommentDateCategory(date: String, category: String) {

}