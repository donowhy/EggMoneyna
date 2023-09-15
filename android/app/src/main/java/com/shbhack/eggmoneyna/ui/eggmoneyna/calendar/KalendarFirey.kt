package com.shbhack.eggmoneyna.ui.eggmoneyna.calendar

/*
 * Copyright 2023 Kalendar Contributors (https://www.himanshoe.com). All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.himanshoe.kalendar.KalendarEvent
import com.himanshoe.kalendar.KalendarEvents
import com.himanshoe.kalendar.color.KalendarColors
import com.himanshoe.kalendar.ui.component.day.KalendarDayKonfig
import com.himanshoe.kalendar.ui.component.header.KalendarHeader
import com.himanshoe.kalendar.ui.component.header.KalendarTextKonfig
import com.himanshoe.kalendar.ui.firey.DaySelectionMode
import com.himanshoe.kalendar.ui.firey.KalendarSelectedDayRange
import com.himanshoe.kalendar.ui.firey.RangeSelectionError
import com.himanshoe.kalendar.ui.oceanic.util.isLeapYear
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.todayIn
import java.time.format.TextStyle
import java.util.Locale

private val WeekDays = listOf("월", "화", "수", "목", "금", "토", "일")

/**
 * Internal composable function representing the Kalendar component.
 *
 * @param currentDay The current selected day in the Kalendar.
 * @param daySelectionMode The day selection mode in the Kalendar.
 * @param modifier The modifier for styling or positioning the Kalendar.
 * @param showLabel Determines whether to show labels in the Kalendar.
 * @param kalendarHeaderTextKonfig The configuration for the Kalendar header text.
 * @param kalendarColors The colors configuration for the Kalendar.
 * @param events The events associated with the Kalendar.
 * @param kalendarDayKonfig The configuration for each day in the Kalendar.
 * @param dayContent Custom content for rendering each day in the Kalendar.
 * @param headerContent Custom content for rendering the header of the Kalendar.
 * @param onDayClick Callback invoked when a day is clicked.
 * @param onRangeSelected Callback invoked when a range of days is selected.
 * @param onErrorRangeSelected Callback invoked when an error occurs during range selection.
 */
private const val TAG = "KalendarFirey_진영"

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun KalendarFirey(
    currentDay: LocalDate?,
    daySelectionMode: DaySelectionMode,
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    kalendarHeaderTextKonfig: KalendarTextKonfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    events: KalendarEvents = KalendarEvents(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    dayContent: (@Composable (LocalDate) -> Unit)? = null,
    headerContent: (@Composable (Month, Int) -> Unit)? = null,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
    onErrorRangeSelected: (RangeSelectionError) -> Unit = {}
) {
    val today = currentDay ?: Clock.System.todayIn(TimeZone.currentSystemDefault())
    val selectedRange = remember { mutableStateOf<KalendarSelectedDayRange?>(null) }
    val selectedDate = remember { mutableStateOf(today) }
    val displayedMonth = remember { mutableStateOf(today.month) }
    val displayedYear = remember { mutableStateOf(today.year) }
    val currentMonth = displayedMonth.value
    val currentYear = displayedYear.value
    val currentMonthIndex = currentMonth.value.minus(1)

    val defaultHeaderColor =
        KalendarTextKonfig(kalendarTextSize = 12.sp, kalendarTextColor = Color.DarkGray)
    val newHeaderTextKonfig = kalendarHeaderTextKonfig ?: defaultHeaderColor

    val daysInMonth = currentMonth.length(currentYear.isLeapYear())
    val monthValue = currentMonth.value.toString().padStart(2, '0')
    val startDayOfMonth = "$currentYear-$monthValue-01".toLocalDate()
    val firstDayOfMonth = startDayOfMonth.dayOfWeek

    Column(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(all = 8.dp)
    ) {
        if (headerContent != null) {
            KalendarHeader2(
                month = currentMonth,
                year = currentYear,
                kalendarTextKonfig = newHeaderTextKonfig,
                onPreviousClick = {
                    displayedYear.value -= if (currentMonth == Month.JANUARY) 1 else 0
                    displayedMonth.value -= 1
                },
                onNextClick = {
                    displayedYear.value += if (currentMonth == Month.DECEMBER) 1 else 0
                    displayedMonth.value += 1
                },
            )
        } else {
            KalendarHeader(
                month = currentMonth,
                year = currentYear,
                kalendarTextKonfig = newHeaderTextKonfig,
                onPreviousClick = {
                    displayedYear.value -= if (currentMonth == Month.JANUARY) 1 else 0
                    displayedMonth.value -= 1
                },
                onNextClick = {
                    displayedYear.value += if (currentMonth == Month.DECEMBER) 1 else 0
                    displayedMonth.value += 1
                },
            )
        }
        // Divider(thickness = 0.5.dp, color = Color.LightGray)
        Spacer(modifier = Modifier.padding(vertical = 4.sdp))
        val dayItemHeight = 32.sdp
        val labelHeight = if (showLabel) 38.sdp else 0.sdp

        // 첫 주의 시작 요일을 고려하여 첫 주의 첫 날짜
        val firstDayOffset = getFirstDayOfMonth(firstDayOfMonth)

        // 전체 날짜 수에 시작 요일을 빼서 나머지 날짜들의 수를 계산
        val daysAfterOffset = daysInMonth - firstDayOffset + 1

        // 첫 주를 제외한 나머지 주의 수 계산
        val fullWeeksAfterFirst = daysAfterOffset / 7

        // 마지막 주에 표시되는 날짜의 수를 계산하여 마지막 주가 필요한지 확인
        val additionalWeek = if (daysAfterOffset % 7 > 0) 1 else 0

        // 총 주의 수
        val totalWeeks = 1 + fullWeeksAfterFirst + additionalWeek

        // 그리드의 총 높이 = (총 주의 수 * 각 항목의 높이) + 요일 레이블의 높이 (조건적으로)
        val gridHeight: Dp = (dayItemHeight * totalWeeks) + if (showLabel) labelHeight else 0.sdp


        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .height(gridHeight),
            userScrollEnabled = false,
            columns = GridCells.Fixed(7),
            content = {
                if (showLabel) {
                    items(WeekDays) { item ->
                        Text(
                            modifier = Modifier,
                            color = Color.Gray,
                            fontSize = kalendarDayKonfig.textSize,
                            text = item,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                items((getFirstDayOfMonth(firstDayOfMonth)..daysInMonth).toList()) {
                    if (it > 0) {
                        val day = calculateDay(it, currentMonth, currentYear)
                        if (dayContent != null) {
                            dayContent(day)
                        } else {
                            KalendarDay(
                                date = day,
                                selectedDate = selectedDate.value,
                                kalendarColors = kalendarColors.color[currentMonthIndex],
                                kalendarEvents = events,
                                kalendarDayKonfig = kalendarDayKonfig,
                                selectedRange = selectedRange.value,
                                onDayClick = { clickedDate, event ->
                                    onDayClicked(
                                        clickedDate,
                                        event,
                                        daySelectionMode,
                                        selectedRange,
                                        onRangeSelected = { range, events ->
                                            if (range.end < range.start) {
                                                onErrorRangeSelected(RangeSelectionError.EndIsBeforeStart)
                                            } else {
                                                onRangeSelected(range, events)
                                            }
                                        },
                                        onDayClick = { newDate, clickedDateEvent ->
                                            selectedDate.value = newDate
                                            // 클릭한 날짜 이벤트 안 넣기
                                            onDayClick(newDate, clickedDateEvent)
                                        }
                                    )
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}


/**
 * Calculates the offset to determine the first day of the month based on the provided first day of the month.
 *
 * @param firstDayOfMonth The first day of the month.
 * @return The offset value representing the first day of the month.
 */
@RequiresApi(Build.VERSION_CODES.O)
private fun getFirstDayOfMonth(firstDayOfMonth: DayOfWeek) = -(firstDayOfMonth.value).minus(2)

/**
 * Calculates a LocalDate object based on the provided day, current month, and current year.
 *
 * @param day The day of the month.
 * @param currentMonth The current month.
 * @param currentYear The current year.
 * @return The LocalDate object representing the specified day, month, and year.
 */
@RequiresApi(Build.VERSION_CODES.O)
private fun calculateDay(day: Int, currentMonth: Month, currentYear: Int): LocalDate {
    val monthValue = currentMonth.value.toString().padStart(2, '0')
    val dayValue = day.toString().padStart(2, '0')
    return "$currentYear-$monthValue-$dayValue".toLocalDate()
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun KalendarHeader2(
    month: Month,
    year: Int,
    kalendarTextKonfig: KalendarTextKonfig,
    modifier: Modifier = Modifier,
    onPreviousClick: () -> Unit = {},
    onNextClick: () -> Unit = {}
) {
    var isNext by remember { mutableStateOf(true) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val titleText = remember(month, year) { getTitleText(month, year) }
        KalendarIconButton(
            modifier = Modifier.wrapContentSize(),
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "Previous Month",
            onClick = {
                isNext = false
                onPreviousClick()
            }
        )
        AnimatedContent(
            targetState = titleText,
            transitionSpec = {
                addAnimation(isNext = isNext).using(
                    SizeTransform(clip = false)
                )
            }, label = ""
        ) { targetText ->
            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically),
                color = kalendarTextKonfig.kalendarTextColor,
                fontSize = 18.sp,
                text = targetText,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start
            )
        }
        KalendarIconButton(
            modifier = Modifier.wrapContentSize(),
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Next Month",
            onClick = {
                isNext = true
                onNextClick()
            }
        )
    }
}

/**
 * Adds the animation to the content based on the given duration and direction.
 *
 * @param duration The duration of the animation in milliseconds.
 * @param isNext Determines the direction of the animation.
 * @return The content transformation with the specified animation.
 */
@OptIn(ExperimentalAnimationApi::class)
private fun addAnimation(duration: Int = 200, isNext: Boolean): ContentTransform {
    return slideInVertically(
        animationSpec = tween(durationMillis = duration)
    ) { height -> if (isNext) height else -height } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) with slideOutVertically(
        animationSpec = tween(durationMillis = duration)
    ) { height -> if (isNext) -height else height } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    )
}

/**
 * Returns the formatted title text for the Kalendar header.
 *
 * @param month The current month.
 * @param year The current year.
 * @return The formatted title text.
 */
@RequiresApi(Build.VERSION_CODES.O)
private fun getTitleText(month: Month, year: Int): String {
    val monthDisplayName = month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        .lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    return "${year}년 $monthDisplayName"
}

@Composable
internal fun KalendarIconButton(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    onClick: () -> Unit = {}
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .wrapContentSize()
            .clip(CircleShape)
    ) {
        Icon(
            modifier = Modifier,
            tint = Color(0xFF413D4B),
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
}

//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//@MultiplePreviews
//private fun KalendarFireyPreview() {
//    KalendarFirey(
//        currentDay = Clock.System.todayIn(
//            TimeZone.currentSystemDefault()
//        ),
//        kalendarHeaderTextKonfig = KalendarTextKonfig.previewDefault(),
//        daySelectionMode = DaySelectionMode.Range
//    )
//}
