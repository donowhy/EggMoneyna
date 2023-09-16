package com.shbhack.eggmoneyna.ui.shinhanmong

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack
import com.shbhack.eggmoneyna.ui.shinhanmong.viewmodel.MonsterViewModel

@Composable
fun ShinhanMongMainScreen(
    navController: NavController,
    viewModel: MonsterViewModel = hiltViewModel()
) {
    val pointItemList = listOf(PointItem(), PointItem(), PointItem())
    val monsterInfo by viewModel.monsterState.collectAsState()
    var gif by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        viewModel.getMyMongDetail()
    }

    LaunchedEffect(monsterInfo) {
        if ((monsterInfo?.status ?: "Egg") != "Egg") {
            if ((monsterInfo?.historyList?.size ?: 0) > 0) {
                var monster = monsterInfo!!.historyList[0].monster
                when(monster.name) {
                    "SOL" -> {
                        when(monster.feel) {
                            "NOMAL" -> R.drawable.gif_sol_normal
                            "SAD" -> R.drawable.gif_sol_sad
                            "HAPPY" -> R.drawable.gif_sol_happy
                        }
                    }
                    "MOLI" -> {
                        when(monster.feel) {
                            "NOMAL" -> R.drawable.gif_mori_normal
                            "SAD" -> R.drawable.gif_mori_sad
                            "HAPPY" -> R.drawable.gif_mori_happy
                        }
                    }
                    "PLY" -> {
                        when(monster.feel) {
                            "NOMAL" -> R.drawable.gif_ply_normal
                            "SAD" -> R.drawable.gif_ply_sad
                            "HAPPY" -> R.drawable.gif_ply_happy
                        }
                    }
                }

            }

        }
    }



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
                CharacterView(gif) {
                    navController.navigate(
                        EggMoneynaDestination.SHINHAN_MON_COLLECTION
                    )
                }
            }
            item {
                Text(
                    modifier = Modifier.padding(20.dp),
                    text = stringResource(id = R.string.shinhanmong_my_shinhanmong_point),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            monsterInfo?.let { monster ->
                items(monster.historyList) { item ->
                    ShinhanMongPointItem(item)
                }
            }
//            items(pointItemList.size) { idx ->
//                ShinhanMongPointItem(pointItemList[idx])
//            }
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