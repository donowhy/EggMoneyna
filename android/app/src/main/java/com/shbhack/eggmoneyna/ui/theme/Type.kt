package com.shbhack.eggmoneyna.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.shbhack.eggmoneyna.R

val pretendard = FontFamily(
    Font(R.font.pretendard_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.pretendard_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.pretendard_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.pretendard_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.pretendard_thin, FontWeight.Thin, FontStyle.Normal),
    Font(R.font.pretendard_black, FontWeight.W900, FontStyle.Normal),
    Font(R.font.pretendard_extrabold, FontWeight.W800, FontStyle.Normal),
    Font(R.font.pretendard_bold, FontWeight.W700, FontStyle.Normal),
    Font(R.font.pretendard_semibold, FontWeight.W600, FontStyle.Normal),
    Font(R.font.pretendard_medium, FontWeight.W500, FontStyle.Normal),
    Font(R.font.pretendard_regular, FontWeight.W400, FontStyle.Normal),
    Font(R.font.pretendard_light, FontWeight.W300, FontStyle.Normal),
    Font(R.font.pretendard_extralight, FontWeight.W200, FontStyle.Normal),
    Font(R.font.pretendard_thin, FontWeight.W100, FontStyle.Normal)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = pretendard
    )
)