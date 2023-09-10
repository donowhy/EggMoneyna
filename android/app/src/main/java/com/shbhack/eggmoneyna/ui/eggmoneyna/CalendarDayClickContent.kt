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
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.theme.EggmoneynaOrange
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn


@Composable
fun DayClickContent(selectedDay: LocalDate) {
    LazyColumn(modifier = Modifier.padding(12.dp)) {
        val selectedDay = Clock.System.todayIn(TimeZone.currentSystemDefault())
        items(5) {
            SpendingListItem(selectedDay)
        }

    }
}

@Composable
fun SpendingListItem(selectedDay: kotlinx.datetime.LocalDate) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = "https://picsum.photos/200",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(68.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.size(12.dp))
        Column(modifier = Modifier.weight(2f)) {
            Text(text = "-4,900원", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Text(
                text = "투썸플레이스 진평점",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Surface(
            color = EggmoneynaOrange,
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 4.dp, bottom = 4.dp),
                text = "카페",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.icon_forward), contentDescription = "forward",
            modifier = Modifier.padding(8.dp), tint = Color.Gray
        )

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(backgroundColor = 0xFFF, showBackground = true)
@Composable
fun CustomCalendarPreview() {
    val selectedDay = Clock.System.todayIn(TimeZone.currentSystemDefault())
    Column {
        CustomCalendar() {

        }
        DayClickContent(selectedDay)
    }
}
