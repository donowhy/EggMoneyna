package com.shbhack.eggmoneyna.ui.eggmoneyna

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.himanshoe.kalendar.KalendarEvents
import com.himanshoe.kalendar.KalendarType
import com.himanshoe.kalendar.color.KalendarColors
import com.himanshoe.kalendar.ui.component.day.KalendarDayKonfig
import com.himanshoe.kalendar.ui.component.header.KalendarTextKonfig
import com.himanshoe.kalendar.ui.firey.DaySelectionMode
import com.himanshoe.kalendar.ui.firey.KalendarSelectedDayRange
import com.himanshoe.kalendar.ui.oceanic.util.isLeapYear
import com.shbhack.eggmoneyna.ui.eggmoneyna.calendar.Kalendar
import com.shbhack.eggmoneyna.ui.eggmoneyna.calendar.KalendarHeader2
import com.shbhack.eggmoneyna.ui.iconpack.MyIconPack
import com.shbhack.eggmoneyna.ui.iconpack.myiconpack.Imagenotfoundmedium
import kotlinx.datetime.Clock
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.todayIn
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomCalendar() {
    val defaultHeaderColor =
        KalendarTextKonfig(kalendarTextSize = 12.sp, kalendarTextColor = Color.DarkGray)
    val newHeaderTextKonfig = null ?: defaultHeaderColor

    Kalendar(
        currentDay = null,
        kalendarType = KalendarType.Firey,
        modifier = Modifier,
        showLabel = true,
        events = KalendarEvents(),
        kalendarHeaderTextKonfig = null,
        kalendarColors = KalendarColors.default(),
        kalendarDayKonfig = KalendarDayKonfig.default(),
        daySelectionMode = DaySelectionMode.Single,
        dayContent = null,
        headerContent = { month, year ->
            KalendarHeader2(month = month, year = year, newHeaderTextKonfig)
        },
        onDayClick = { selectedDay, events ->
            // Handle day click event
        }

    )

}

@Composable
fun DayClickContent() {
    Column(modifier = Modifier.padding(12.dp)){
        SpendingListItem()
    }
}

@Composable
fun SpendingListItem() {
    Row() {
        AsyncImage(
            model = "https://dummyimage.com/600x400/000/fff.png&text=hello",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(8.dp)),
            error = rememberVectorPainter(image = MyIconPack.Imagenotfoundmedium)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(backgroundColor = 0xFFFFFFF)
@Composable
fun CustomCalendarPreview() {
    Column {
        CustomCalendar()
        DayClickContent()
    }
}
