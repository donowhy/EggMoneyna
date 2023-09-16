package com.shbhack.eggmoneyna.ui.comment

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack
import com.shbhack.eggmoneyna.ui.theme.keyColorLight1
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CommentScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopWithBack(
                navController = navController,
                title = stringResource(id = R.string.comment_appbar_title)
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())
        )
        {
            Column(
                modifier = Modifier
            ) {
                CommentBanner(
                    title = "스타벅스 인동점",
                    content = "-6,700원",
                    "https://picsum.photos/200"
                ) {
                    // 칭찬하기 클릭
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.sdp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CommentDateCategory("일시", "2023년 8월 24일 20:13")
                    Spacer(modifier = Modifier.size(8.sdp))
                    CommentDateCategory("카테고리", "카페")
                }


                Divider(
                    thickness = 12.sdp,
                    color = keyColorLight1
                )

                Text(
                    modifier = Modifier.padding(12.sdp),
                    text = "지출 코멘트",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )

                Divider(
                    modifier = Modifier.padding(start = 12.sdp, end = 12.sdp),
                    thickness = 1.sdp
                )
                Spacer(modifier = Modifier.size(12.sdp))
                CommentItemComponent(
                    R.drawable.icon_profile,
                    "우리 공주 민승",
                    "오늘 공부를 하기 위해 카페에서 초코라떼를 시켜먹었어요",
                    "1일전"
                )
                // 부모의 댓글 여부에 따라서 나타내기
                CommentItemComponent(
                    R.drawable.icon_profile,
                    "엄마",
                    "민승아 오늘 공부하러 카페에 간다더니 초코라떼를 먹었구나^^ 잘했어!",
                    "1일전"
                )
            }
            // textField
            var nickNameValue by remember { mutableStateOf("") }
            val keyboardController = LocalSoftwareKeyboardController.current
            TextField(
                value = nickNameValue,
                onValueChange = { nickNameValue = it },
                textStyle = TextStyle(
                    fontSize = 12.ssp,
                    fontWeight = FontWeight.Normal
                ),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = androidx.compose.ui.text.input.ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = {
                    // 보내기 버튼
                    keyboardController?.hide()
                }),
                trailingIcon = {
                    IconButton(onClick = {
                        // 보내기 버튼
                        keyboardController?.hide()
                    }) {
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
                    .border(1.sdp, Color.LightGray, RoundedCornerShape(12.sdp))
                    .align(Alignment.BottomCenter),
                shape = ShapeDefaults.Medium,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview
@Composable
fun CommentScreenPreview() {
    CommentScreen(navController = rememberNavController())
}