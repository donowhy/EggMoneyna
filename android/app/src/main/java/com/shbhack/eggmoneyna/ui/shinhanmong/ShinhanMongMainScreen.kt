package com.shbhack.eggmoneyna.ui.shinhanmong

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack

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
                CharacterView {
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