package com.shbhack.eggmoneyna.ui.comment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.common.component.CustomSurfaceWithText
import com.shbhack.eggmoneyna.ui.theme.bannerBlue2
import com.shbhack.eggmoneyna.ui.theme.blueColor
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp


@Composable
fun CommentBanner(
    title: String,
    content: String,
    img: String?,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = bannerBlue2)
            .padding(start = 32.dp, end = 12.dp, bottom = 32.dp, top = 12.dp)
    ) {
        Surface(
            modifier = Modifier
                .wrapContentHeight()
                .align(Alignment.End)
                .clickable {
                    onClick()
                },
            color = blueColor,
            shape = RoundedCornerShape(40.dp),
        ) {
            Text(
                modifier = Modifier.padding(
                    start = 10.sdp,
                    end = 10.sdp,
                    top = 4.sdp,
                    bottom = 4.sdp
                ),
                text = "칭찬하기",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 8.ssp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = title, fontWeight = FontWeight.Medium, fontSize = 18.sp, color = Color.White
            )
            Spacer(modifier = Modifier.size(4.sdp))
            AsyncImage(
                model = img,
                contentDescription = "지출 브랜드 로고",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(20.sdp)
                    .clip(CircleShape)
            )
        }

        Spacer(
            modifier = Modifier.size(4.dp)
        )
        Text(
            text = content, fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp, color = Color.White
        )
    }
}

@Composable
fun CommentDateCategory(title: String, content: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title, fontWeight = FontWeight.Medium,
            fontSize = 12.ssp
        )
        if (title == "카테고리") {
            CustomSurfaceWithText(category = content)
        } else {
            Text(
                text = content, fontWeight = FontWeight.Normal,
                fontSize = 12.ssp
            )
        }
    }

}

@Composable
fun CommentItemComponent(imgId: Int, name: String, content: String, time: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.sdp, end = 16.sdp, top = 10.sdp, bottom = 10.sdp)
            .padding(start = 4.sdp)
    ) {
        Image(
            painter = painterResource(id = imgId),
            contentDescription = "프로필 이미지",
            modifier = Modifier.size(52.sdp)
        )
        Spacer(modifier = Modifier.size(12.sdp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = name, fontSize = 12.ssp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.size(2.sdp))
            Text(
                text = content,
                style = TextStyle(
                    fontSize = 12.ssp,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )
            )
            Text(
                text = time, modifier = Modifier.fillMaxWidth(),
                fontSize = 10.ssp, fontWeight = FontWeight.Normal,
                textAlign = TextAlign.End
            )
        }
        Spacer(modifier = Modifier.size(12.sdp))

    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CommentTextField(modifier: Modifier = Modifier, submit: (keyword: String) -> Unit = {}) {
    // textField
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        onValueChange = { text = it },
        textStyle = TextStyle(
            fontSize = 12.ssp,
            fontWeight = FontWeight.Normal
        ),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            submit(text)
            keyboardController?.hide()
        }),
        leadingIcon = {
            IconButton(onClick = { submit(text) }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_send),
                    contentDescription = "Trailing icon",
                    modifier = Modifier.fillMaxSize(0.7f)
                )
            }
        },
        placeholder = {
            Text(
                "코멘트를 입력하세요",
                fontWeight = FontWeight.Light,
                fontSize = 14.sp
            )
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.sdp)
            .clip(RoundedCornerShape(12.sdp))
            .border(1.sdp, Color.LightGray, RoundedCornerShape(12.sdp)),
        shape = ShapeDefaults.Medium,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent
        )
    )
}