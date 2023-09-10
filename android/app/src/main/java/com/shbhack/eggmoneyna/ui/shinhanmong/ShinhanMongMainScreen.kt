package com.shbhack.eggmoneyna.ui.shinhanmong

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.common.animation.EggAnimationView
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack
import com.shbhack.eggmoneyna.ui.theme.EggmoneynaOrange
import com.shbhack.eggmoneyna.ui.theme.keyColorLight1
import com.shbhack.eggmoneyna.ui.theme.keyColorLight2

@Composable
fun ShinhanMongMainScreen(navController: NavController) {
    val pointItemList = listOf(PointItem(), PointItem(), PointItem())

    Scaffold(
        topBar = {
            TopWithBack(
                navController = navController,
                title = stringResource(id = R.string.shinhanmong_appbar_title)
            )
        }) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
        ) {
            item {
                CharacterView()
            }
            item {
                Text(
                    modifier = Modifier.padding(20.dp),
                    text = "나의 신한몽 포인트", fontSize = 16.sp, fontWeight = FontWeight.SemiBold
                )
            }
            items(pointItemList.size) { idx ->
                ShinhanMongPointItem(pointItemList[idx])
            }
        }
    }
}

@Preview
@Composable
fun ShinhanMongMainScreenPreview() {
    ShinhanMongMainScreen(navController = rememberNavController())
}

data class PointItem(
    var name: String = "출석 포인트 적립",
    var point: Int = 5,
    var totalPoint: Int = 20,
    var date: String = "2023. 08. 24"
)