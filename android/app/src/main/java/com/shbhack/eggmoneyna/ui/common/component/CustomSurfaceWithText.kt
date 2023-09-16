package com.shbhack.eggmoneyna.ui.common.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.shbhack.eggmoneyna.ui.theme.EggmoneynaOrange
import com.shbhack.eggmoneyna.ui.theme.PieChartColor1
import com.shbhack.eggmoneyna.ui.theme.PieChartColor2
import com.shbhack.eggmoneyna.ui.theme.PieChartColor3
import com.shbhack.eggmoneyna.ui.theme.PieChartColor4
import com.shbhack.eggmoneyna.ui.theme.PieChartColor5
import com.shbhack.eggmoneyna.ui.theme.PieChartColor6
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun CustomSurfaceWithText(category: String) {
    // 카테고리 : 편의점, 외식, 카페, 쇼핑, 오락
    val color = when (category) {
        "편의점" -> PieChartColor1
        "외식" -> PieChartColor2
        "카페" -> PieChartColor3
        "쇼핑" -> PieChartColor4
        "오락" -> PieChartColor5
        else -> PieChartColor6
    }

    Surface(
        color = color,
        shape = RoundedCornerShape(16.sdp)
    ) {
        Text(
            modifier = Modifier.padding(
                start = 10.sdp,
                end = 10.sdp,
                top = 4.sdp,
                bottom = 4.sdp
            ),
            text = category,
            fontSize = 10.ssp,
            fontWeight = FontWeight.Medium
        )
    }
}
