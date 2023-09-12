package com.shbhack.eggmoneyna.ui.shinhanmong.collection.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.navigation.compose.rememberNavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.theme.keyColorLight1
import com.shbhack.eggmoneyna.ui.theme.keyColorLight2
import com.shbhack.eggmoneyna.util.DateUtils
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ShinhanMongCollectionDetailView(paddingValues: PaddingValues) {
    val collectionInfo = ShinHanMong(
        name = "몰리",
        image = painterResource(id = R.drawable.img_shinhanmong2),
        outline = "식물 카페의 느긋한 사장님",
        birthday = "2023-08-10",
        story = "항상 느긋하고 세상 태평한 식물 집사, 두더지 몰리\n최근 제주도에 오픈한 식물 카페로 진정한 휴식을 즐기러 오세요."
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(keyColorLight1)
            .padding(paddingValues)
            .padding(20.sdp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(8.sdp))
        CollectionImage(image = collectionInfo.image)
        Spacer(modifier = Modifier.size(16.sdp))
        CollectionOutline(name = collectionInfo.name, outline = collectionInfo.outline)
        Spacer(modifier = Modifier.size(20.sdp))
        CollectionCardView(
            title = "알이 태어난 날",
            content = DateUtils.convertDateFormat(collectionInfo.birthday),
            TextStyle(fontSize = 16.ssp, fontWeight = FontWeight.Medium)
        )
        Spacer(modifier = Modifier.size(10.sdp))
        CollectionCardView(
            title = "${collectionInfo.name}와 함께한 지",
            content = DateUtils.calculateDday(collectionInfo.birthday),
            TextStyle(fontSize = 16.ssp, fontWeight = FontWeight.Medium)
        )
        Spacer(modifier = Modifier.size(10.sdp))
        CollectionCardView(
            title = "${collectionInfo.name}의 스토리",
            content = collectionInfo.story,
            TextStyle(fontSize = 12.ssp, fontWeight = FontWeight.Medium, lineHeight = 16.ssp)
        )
    }
}

@Composable
fun CollectionImage(image: Painter) {
    Image(
        modifier = Modifier.size(200.sdp),
        painter = image,
        contentDescription = "신한몽"
    )
}

@Composable
fun CollectionOutline(name: String, outline: String) {
    Surface(
        modifier = Modifier,
        color = keyColorLight2,
        shape = RoundedCornerShape(10.sdp),
    ) {
        Text(
            modifier = Modifier.padding(start = 10.sdp, end = 10.sdp, top = 4.sdp, bottom = 4.sdp),
            text = "$outline '${name}'",
            style = TextStyle(fontSize = 14.ssp, fontWeight = FontWeight.Medium)
        )
    }
}

@Composable
fun CollectionCardView(title: String, content: String, contentTextStyle: TextStyle) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.sdp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.sdp)
    ) {
        Text(
            modifier = Modifier.padding(start = 14.sdp, end = 14.sdp, top = 14.sdp, bottom = 4.sdp),
            text = title,
            style = TextStyle(
                fontSize = 12.ssp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        )
        Text(
            modifier = Modifier.padding(start = 14.sdp, end = 14.sdp, bottom = 14.sdp),
            text = content,
            style = contentTextStyle
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ShinhanMongCollectionDetailScreenPrivew() {
    ShinhanMongCollectionDetailScreen(navController = rememberNavController())
}

data class ShinHanMong(
    val name: String,
    val image: Painter,
    val outline: String,
    val birthday: String,
    val story: String
)