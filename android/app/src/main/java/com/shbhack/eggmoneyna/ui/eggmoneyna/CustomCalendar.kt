package com.shbhack.eggmoneyna.ui.eggmoneyna

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.himanshoe.kalendar.KalendarEvents
import com.himanshoe.kalendar.KalendarType
import com.himanshoe.kalendar.color.KalendarColor
import com.himanshoe.kalendar.color.KalendarColors
import com.himanshoe.kalendar.ui.component.day.KalendarDayKonfig
import com.himanshoe.kalendar.ui.component.header.KalendarTextKonfig
import com.himanshoe.kalendar.ui.firey.DaySelectionMode
import com.shbhack.eggmoneyna.ui.eggmoneyna.calendar.Kalendar
import com.shbhack.eggmoneyna.ui.eggmoneyna.calendar.KalendarHeader2
import com.shbhack.eggmoneyna.ui.theme.logoColor
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomCalendar(
    events: KalendarEvents = KalendarEvents(),
    onDayClick: (
        kotlinx.datetime.LocalDate
    ) -> Unit
) {
    val defaultHeaderColor =
        KalendarTextKonfig(kalendarTextSize = 12.sp, kalendarTextColor = Color.DarkGray)
    val newHeaderTextKonfig = null ?: defaultHeaderColor

    Kalendar(
        currentDay = null,
        kalendarType = KalendarType.Firey,
        modifier = Modifier.padding(start = 12.sdp, end = 12.sdp),
        showLabel = true,
        events = events,
        kalendarHeaderTextKonfig = null,
        kalendarColors = KalendarColors(color = List(12) {
            KalendarColor(
                backgroundColor = Color.White,
                dayBackgroundColor = logoColor,
                headerTextColor = Color.Black
            )
        }),
        kalendarDayKonfig = KalendarDayKonfig(
            size = 20.sdp,
            textSize = 12.ssp,
            textColor = Color.Black,
            selectedTextColor = Color.White,
            borderColor = Color.Transparent
        ),
        daySelectionMode = DaySelectionMode.Single,
        dayContent = null,
        headerContent = { month, year ->
            KalendarHeader2(month = month, year = year, newHeaderTextKonfig)
        },
        onDayClick = { selectedDay, events ->
            onDayClick(selectedDay)
        }

    )

}
