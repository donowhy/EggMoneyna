package com.shbhack.eggmoneyna.ui.eggmoneyna

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.common.component.ColorBackgroundWithText
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack
import com.shbhack.eggmoneyna.ui.theme.EggmoneynaPurple
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EggMoneynaScreen(
    navController: NavController
) {
    var selectedDay by remember { mutableStateOf(Clock.System.todayIn(TimeZone.currentSystemDefault())) }
    Scaffold(
        topBar = {
            TopWithBack(
                navController = navController,
                title = stringResource(id = R.string.eggmoneyna_appbar_title)
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            LazyColumn {
                item {
                    ColorBackgroundWithText(EggmoneynaPurple, stringResource(id = R.string.eggmoneyna_rest_money_title), "500,000원")
                }
                item {
                    CustomCalendar() { localDate ->
                        selectedDay = localDate
                    }
                }
                items(3) {
                    SpendingListItem(selectedDay)
                }
            }


        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun EggMoneynaScreenPreview() {
    EggMoneynaScreen(rememberNavController())
}