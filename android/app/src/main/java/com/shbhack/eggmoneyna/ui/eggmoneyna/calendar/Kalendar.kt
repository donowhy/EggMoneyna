package com.shbhack.eggmoneyna.ui.eggmoneyna.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.himanshoe.kalendar.KalendarEvent
import com.himanshoe.kalendar.KalendarEvents
import com.himanshoe.kalendar.KalendarType
import com.himanshoe.kalendar.color.KalendarColors
import com.himanshoe.kalendar.ui.component.day.KalendarDayKonfig
import com.himanshoe.kalendar.ui.component.header.KalendarTextKonfig
import com.himanshoe.kalendar.ui.firey.DaySelectionMode
import com.himanshoe.kalendar.ui.firey.KalendarSelectedDayRange
import com.himanshoe.kalendar.ui.firey.RangeSelectionError
import kotlinx.datetime.LocalDate
import java.time.Month

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Kalendar(
    currentDay: LocalDate?,
    kalendarType: KalendarType,
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    events: KalendarEvents = KalendarEvents(),
    kalendarHeaderTextKonfig: KalendarTextKonfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    daySelectionMode: DaySelectionMode = DaySelectionMode.Single,
    dayContent: (@Composable (LocalDate) -> Unit)? = null,
    headerContent: (@Composable (Month, Int) -> Unit)? = null,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
    onErrorRangeSelected: (RangeSelectionError) -> Unit = {}
) {
    if (kalendarType == KalendarType.Firey) {
        KalendarFirey(
            currentDay = currentDay,
            modifier = modifier,
            showLabel = showLabel,
            kalendarHeaderTextKonfig = kalendarHeaderTextKonfig,
            kalendarColors = kalendarColors,
            events = events,
            kalendarDayKonfig = kalendarDayKonfig,
            onDayClick = onDayClick,
            dayContent = dayContent,
            headerContent = headerContent,
            daySelectionMode = daySelectionMode,
            onRangeSelected = onRangeSelected,
            onErrorRangeSelected = onErrorRangeSelected
        )
    }

}
