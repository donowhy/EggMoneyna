package com.shbhack.eggmoneyna.ui.shinhanmong

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shbhack.eggmoneyna.data.model.HistoryItem
import com.shbhack.eggmoneyna.util.DateUtils

@Composable
fun ShinhanMongPointItem(pointItem: HistoryItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 12.dp)
    ) {
        PointItemRowText(
            text1 = pointItem.content,
            text2 = "+${pointItem.exp} EXP",
            14,
            Color.Black,
            FontWeight.Medium
            )
        PointItemRowText(
            text1 = DateUtils.formatyyyymmdd(pointItem.updateTime),
            text2 = "${pointItem.prefix} EXP",
            12,
            Color.Gray,
            FontWeight.Normal
        )
    }

}

@Composable
fun PointItemRowText(text1: String, text2: String, fontSize: Int, fontColor: Color, fontWeight: FontWeight) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = text1, fontSize = fontSize.sp, color = fontColor, fontWeight = fontWeight, modifier = Modifier.weight(1f, fill = false))
        Text(text = text2, fontSize = fontSize.sp, color = fontColor, fontWeight = fontWeight, modifier = Modifier.weight(1f, fill = false))
    }
}

@Preview(showBackground = true)
@Composable
fun ShinhanMongPointViewPreview() {
//    ShinhanMongPointItem(PointItem())
}