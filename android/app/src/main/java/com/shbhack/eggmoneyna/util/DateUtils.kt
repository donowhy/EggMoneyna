package com.shbhack.eggmoneyna.util

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale

object DateUtils {
    private const val MINUTE = 60L
    private const val HOUR = 3600L
    private const val DAY = 86400L
    private const val MONTH = 2592000L
    private const val YEAR = 31536000L

    @RequiresApi(Build.VERSION_CODES.O)
    fun dateToString(timeString: String): String {
        val time = timeString.slice(0..22)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val dateTime = LocalDateTime.parse(time, formatter)

        val currentTime = LocalDateTime.now()
        val timeDifference = ChronoUnit.SECONDS.between(dateTime, currentTime)

        return when {
            timeDifference < MINUTE -> "방금 전"
            timeDifference < HOUR -> "${timeDifference / MINUTE}분 전"
            timeDifference < DAY -> "${timeDifference / HOUR}시간 전"
            timeDifference < MONTH -> "${timeDifference / DAY}일 전"
            timeDifference < YEAR -> "${timeDifference / MONTH}달 전"
            else -> "${timeDifference / YEAR}년 전"
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun convertMillisToYymmdd(millis: Long): String {
        val dateFormat = SimpleDateFormat("yy. MM. dd")
        val date = Date(millis)
        return dateFormat.format(date)
    }

    @SuppressLint("SimpleDateFormat")
    fun convertDateFormat(inputDate: String): String {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd")
        val targetFormat = SimpleDateFormat("yyyy년 MM월 dd일")

        val date = originalFormat.parse(inputDate)
        return targetFormat.format(date)
    }


    // yyyy-MM-dd 형식의 문자열을 대상 날짜로 받아, 현재 날짜와의 차이를 일자로 반환
    @RequiresApi(Build.VERSION_CODES.O)
    fun calculateDday(targetDate: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val targetLocalDate = LocalDate.parse(targetDate, formatter)
        val currentLocalDate = LocalDate.now()

        val daysDifference = ChronoUnit.DAYS.between(targetLocalDate, currentLocalDate).toInt()

        return "+${daysDifference}일"
    }

    fun formatToYearMonth(dateString: String): String {
        return if (dateString.matches("""\d{4}-\d{2}-\d{2}""".toRegex())) {
            dateString.substring(0, 7)
        } else {
            throw IllegalArgumentException("Invalid date format: $dateString")
        }
    }

    @SuppressLint("NewApi")
    fun getDayOfWeekFromDateString(dateString: String): String {
        val date = LocalDate.parse(dateString)
        return when (date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())) {
            "Mon" -> "월"
            "Tue" -> "화"
            "Wed" -> "수"
            "Thu" -> "목"
            "Fri" -> "금"
            "Sat" -> "토"
            "Sun" -> "일"
            else -> ""
        }
    }


}