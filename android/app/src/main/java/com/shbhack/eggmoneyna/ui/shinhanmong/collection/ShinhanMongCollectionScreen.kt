package com.shbhack.eggmoneyna.ui.shinhanmong.collection

import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack

@Composable
fun ShinhanMongCollectionScreen(navController: NavController) {
    var shinHanMongItems = listOf(
        ShinHanMongCollection(
            image = painterResource(id = R.drawable.img_shinhanmong1),
            collected = true
        ),
        ShinHanMongCollection(
            image = painterResource(id = R.drawable.img_shinhanmong2),
            collected = true
        ),
        ShinHanMongCollection(
            image = painterResource(id = R.drawable.img_shinhanmong3),
            collected = true
        ),
        ShinHanMongCollection(
            image = painterResource(id = R.drawable.img_shinhanmong4),
            collected = false
        ),
        ShinHanMongCollection(
            image = painterResource(id = R.drawable.img_shinhanmong5),
            collected = false
        ),
        ShinHanMongCollection(
            image = painterResource(id = R.drawable.img_shinhanmong6),
            collected = false
        ),
        ShinHanMongCollection(
            image = painterResource(id = R.drawable.img_shinhanmong7),
            collected = false
        ),
        ShinHanMongCollection(
            image = painterResource(id = R.drawable.img_shinhanmong8),
            collected = true
        ),
    )
    Scaffold(
        topBar = {
            TopWithBack(
                navController = navController,
                title = stringResource(id = R.string.shinhanmong_collection_appbar_title)
            )
        }) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(it)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            items(shinHanMongItems.size) { idx ->
                ShinHanMongImage(shinHanMongItems[idx])
            }

        }
    }
}

@Composable
fun ShinHanMongImage(item: ShinHanMongCollection, onClick: () -> Unit = {}) {
    var color = if (!item.collected) ColorFilter.tint(Color.DarkGray.copy(alpha = 0.9f), blendMode = BlendMode.SrcAtop) else null
    var imageModifier = Modifier.height(152.dp)

    // 도감을 등록하였다면 클릭 가능하게 변경
    if (item.collected) {
        imageModifier = imageModifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null // 클릭 효과 제거
        ) { onClick() }
    }


    Image(
        modifier = imageModifier,
        painter = item.image,
        contentDescription = "신한몽",
        colorFilter = color
    )
}


@Preview
@Composable
fun ShinhanMongCollectionScreenPreview() {
    ShinhanMongCollectionScreen(navController = rememberNavController())
}

data class ShinHanMongCollection(
    val image: Painter,
    val collected: Boolean
)
