package com.shbhack.eggmoneyna.ui.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.data.local.AppPreferences
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack
import com.shbhack.eggmoneyna.ui.common.util.noRippleClickable
import com.shbhack.eggmoneyna.ui.theme.keyColor1
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp


@Composable
fun SettingScreen(navController: NavController, viewModel: SettingViewModel = hiltViewModel()) {
    val threshold by viewModel.thresholdState.collectAsState()

    Scaffold(
        topBar = {
            TopWithBack(
                navController = navController,
                title = stringResource(id = R.string.setting_appbar_title)
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.sdp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_profile),
                    contentDescription = "프로필 이미지",
                    modifier = Modifier.size(120.sdp)
                )

                Text(
                    modifier = Modifier
                        .padding(start = 2.sdp)
                        .align(alignment = Alignment.Start),
                    text = "닉네임",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.ssp
                    )
                )
                Spacer(modifier = Modifier.size(8.sdp))
                var nickNameValue by remember { mutableStateOf("닉네임") }
                TextField(
                    value = nickNameValue,
                    onValueChange = { nickNameValue = it },
                    textStyle = TextStyle(
                        fontSize = 12.ssp,
                        fontWeight = FontWeight.Normal
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
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
                Spacer(modifier = Modifier.size(24.dp))

                // 지출 임계치 설정
                Text(
                    modifier = Modifier
                        .padding(start = 2.sdp)
                        .align(alignment = Alignment.Start),
                    text = "지출 임계치 설정",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.ssp
                    )
                )
                Spacer(modifier = Modifier.size(8.sdp))

                var text by remember { mutableStateOf(threshold.toString()) }


                TextField(
                    value = text,
                    onValueChange = { text = it },
                    textStyle = TextStyle(
                        fontSize = 12.ssp,
                        fontWeight = FontWeight.Normal
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
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
                Spacer(modifier = Modifier.size(24.dp))
                // 알림 설정
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 2.sdp)
                        .align(Alignment.Start),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(

                        text = "앱 알림 설정",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.ssp
                        )
                    )

                    Toggle()
                }


            }


            Text(
                text = "로그아웃",
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 64.sdp, end = 16.sdp)
                    .clickable {
                        AppPreferences.logout()
                    },
            )
            Button(
                onClick = {
                    // 버튼 클릭시 수행할 동작
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.sdp),
                shape = ShapeDefaults.Medium,
                colors = ButtonDefaults.buttonColors(containerColor = keyColor1)
            ) {
                Text("정보 수정")
            }
        }

    }
}

@Composable
fun Toggle() {
    var isChecked by remember { mutableStateOf(false) }

    Switch(
        checked = isChecked,
        onCheckedChange = { isChecked = !isChecked },
        colors = SwitchDefaults.colors(
            checkedTrackColor = keyColor1,
            uncheckedTrackColor = Color.White,
            uncheckedBorderColor = Color.LightGray
        )
    )

}

@Preview
@Composable
fun SettingScreenPreview() {
    SettingScreen(navController = rememberNavController())
}
