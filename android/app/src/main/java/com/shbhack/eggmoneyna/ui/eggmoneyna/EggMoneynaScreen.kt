package com.shbhack.eggmoneyna.ui.eggmoneyna

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.himanshoe.kalendar.KalendarEvent
import com.himanshoe.kalendar.KalendarEvents
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.data.local.AppPreferences
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination
import com.shbhack.eggmoneyna.ui.common.component.ColorBackgroundWithText
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack
import com.shbhack.eggmoneyna.ui.eggmoneyna.viewModel.EggMoneynaViewModel
import com.shbhack.eggmoneyna.ui.theme.EggmoneynaPurple
import com.shbhack.eggmoneyna.ui.theme.keyColor1
import com.shbhack.eggmoneyna.util.DateUtils
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

private const val TAG = "EggMoneynaScreen_진영"

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EggMoneynaScreen(
    navController: NavController, eggMoneynaViewModel: EggMoneynaViewModel = hiltViewModel()
) {
    var selectedDay by remember { mutableStateOf(Clock.System.todayIn(TimeZone.currentSystemDefault())) }
    val complements by eggMoneynaViewModel.complimentsState.collectAsState()
    val inputOutput by eggMoneynaViewModel.inputOutputsState.collectAsState()

    LaunchedEffect(Unit) {
        eggMoneynaViewModel.getCompliments(DateUtils.formatToYearMonth(selectedDay.toString()))
    }

    LaunchedEffect(selectedDay) {
        eggMoneynaViewModel.getInputOutput(selectedDay.toString())
    }

    LaunchedEffect(inputOutput) {
        Log.d(TAG, "EggMoneynaScreen: ${inputOutput.inputOutputs}")
    }

    var kalendarEvents by remember { mutableStateOf(KalendarEvents()) }

    LaunchedEffect(complements) {
        val eventsList = complements.map {
            KalendarEvent(
                date = LocalDate.parse(it.localDate),
                eventName = "eventName",
                eventDescription = if (it.compliment) "Received a compliment" else "No compliment"
            )
        }
        kalendarEvents = KalendarEvents(eventsList)
    }

    var title =
        if (AppPreferences.isParent()) stringResource(id = R.string.eggmoneyna_rest_money_title_parent) else stringResource(
            id = R.string.eggmoneyna_rest_money_title_child
        )

    Scaffold(
        topBar = {
            TopWithBack(
                navController = navController,
                title = stringResource(id = R.string.eggmoneyna_appbar_title)
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LazyColumn {
                item {
                    ColorBackgroundWithText(
                        EggmoneynaPurple,
                        title,
                        "500,000원"
                    )
                }
                item {
                    CustomCalendar(
                        kalendarEvents
//                        KalendarEvents(
//                            listOf(
//                                KalendarEvent(
//                                    LocalDate(2023, 9, 11),
//                                    "",
//                                    ""
//                                ),
//                                KalendarEvent(
//                                    LocalDate(2023, 9, 14),
//                                    "dd",
//                                    "dd"
//                                ),
//                                KalendarEvent(
//                                    LocalDate(2023, 9, 17),
//                                    "dd",
//                                    "dd"
//                                )
//                            )
//                        )
                    ) { localDate ->
                        // 날짜 선택 할 때마다 지출 내역 불러오기

                        selectedDay = localDate
                    }
                }
                items(inputOutput.inputOutputs) { item ->
                    SpendingListItem(selectedDay, item) {
                        navController.navigate(EggMoneynaDestination.EXPENSE_COMMENT)
                    }
                }
                item {
                    Spacer(modifier = Modifier.size(32.sdp))
                }

            }

            // 지출 분석 버튼
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.sdp, end = 16.sdp)
                    .align(Alignment.BottomCenter),
                shape = ShapeDefaults.Medium,
                colors = ButtonDefaults.buttonColors(keyColor1),
                onClick = {
                    navController.navigate(EggMoneynaDestination.EXPENSE_ANALYSIS)
                }) {
                // 부모 -> 자녀 지출 분석
                // 아이 -> 나의 지출 분석
                Text(
                    text = "자녀 지출 분석", style = TextStyle(
                        fontSize = 12.ssp, fontWeight = FontWeight.SemiBold
                    )
                )
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