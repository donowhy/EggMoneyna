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
import coil.compose.AsyncImage
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.common.util.noRippleClickable
import com.shbhack.eggmoneyna.ui.theme.EggmoneynaOrange
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn


@Composable
fun DayClickContent(selectedDay: LocalDate) {
    LazyColumn(modifier = Modifier.padding(12.sdp)) {
        val selectedDay = Clock.System.todayIn(TimeZone.currentSystemDefault())
        items(5) {
//            SpendingListItem(selectedDay)
        }

    }
}

@Composable
fun SpendingListItem(selectedDay: kotlinx.datetime.LocalDate, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.sdp, end = 16.sdp, top = 10.sdp, bottom = 10.sdp)
            .padding(start = 4.sdp)
            .noRippleClickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = "https://picsum.photos/200",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(58.sdp)
                .clip(CircleShape)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.size(12.sdp))
        Column(modifier = Modifier.weight(2f)) {
            Text(text = "-4,900원", fontSize = 12.ssp, fontWeight = FontWeight.SemiBold)
            Text(
                text = "투썸플레이스 진평점",
                fontSize = 10.ssp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.size(12.sdp))
        Surface(
            color = EggmoneynaOrange,
            shape = RoundedCornerShape(16.sdp),
        ) {
            Text(
                modifier = Modifier.padding(
                    start = 10.sdp,
                    end = 10.sdp,
                    top = 4.sdp,
                    bottom = 4.sdp
                ),
                text = "카페",
                fontSize = 10.ssp,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.size(8.sdp))
        Icon(
            painter = painterResource(id = R.drawable.icon_forward), contentDescription = "forward",
            modifier = Modifier.size(16.sdp), tint = Color.Gray
        )

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(backgroundColor = 0xFFF, showBackground = true)
@Composable
fun CustomCalendarPreview() {
    val selectedDay = Clock.System.todayIn(TimeZone.currentSystemDefault())
    Column {
        CustomCalendar {

        }
//        DayClickContent(selectedDay)
    }
}
