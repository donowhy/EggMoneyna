package com.shbhack.eggmoneyna.ui.common.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.theme.EggmoneynaOrange

@Composable
fun RoundedCornerButton(roundedSize : Int,color: Color, text: String, fontSize: Int, fontColor: Color) {
    Surface(
        modifier = Modifier
            .clickable() {

            },
        color = color,
        shape = RoundedCornerShape(roundedSize.dp),
    ) {
        Text(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 4.dp, bottom = 4.dp),
            color = fontColor,
            text = text,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Medium
        )
    }
}