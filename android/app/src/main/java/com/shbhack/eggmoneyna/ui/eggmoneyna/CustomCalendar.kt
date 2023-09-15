package com.shbhack.eggmoneyna.ui.eggmoneyna

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.himanshoe.kalendar.KalendarEvents
import com.himanshoe.kalendar.KalendarType
import com.himanshoe.kalendar.color.KalendarColor
import com.himanshoe.kalendar.color.KalendarColors
import com.himanshoe.kalendar.ui.component.day.KalendarDayKonfig
import com.himanshoe.kalendar.ui.component.header.KalendarTextKonfig
import com.himanshoe.kalendar.ui.firey.DaySelectionMode
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.eggmoneyna.calendar.Kalendar
import com.shbhack.eggmoneyna.ui.eggmoneyna.calendar.KalendarHeader2
import com.shbhack.eggmoneyna.ui.theme.EggmoneynaOrange
import com.shbhack.eggmoneyna.ui.theme.logoColor
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

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
        modifier = Modifier.padding(start = 12.dp, end = 12.dp),
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
            size = 20.dp,
            textSize = 14.sp,
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
