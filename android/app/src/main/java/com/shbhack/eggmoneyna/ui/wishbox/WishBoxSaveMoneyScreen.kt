package com.shbhack.eggmoneyna.ui.wishbox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination
import com.shbhack.eggmoneyna.ui.common.button.ButtonRadius10
import com.shbhack.eggmoneyna.ui.common.lottie.LottieLoader
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack
import com.shbhack.eggmoneyna.ui.theme.keyColor1
import com.shbhack.eggmoneyna.ui.theme.keyColorLight1
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishBoxSaveMoneyScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopWithBack(
                navController = navController,
                title = "위시 박스 채우기"
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(15.sdp)
                .fillMaxSize()
        ) {
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "보관할 금액을\n입력해주세요",
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.ssp)
                )
                Spacer(modifier = Modifier.width(30.sdp))
                LottieLoader(
                    source = R.raw.wishbox_recommend, modifier = Modifier
                        .width(0.sdp)
                        .weight(1f)
                        .aspectRatio(1f)
                )
            }

            Spacer(modifier = Modifier.height(40.sdp))


            var text by remember { mutableStateOf(TextFieldValue("")) }

            OutlinedTextField(
                value = text,
                onValueChange = { if (it.text.length <= 12) text = it },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = 13.ssp),
                placeholder = {
                    Text(
                        "보관 금액",
                        style = TextStyle(fontSize = 13.ssp)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = keyColor1,
                )
            )

            Spacer(modifier = Modifier.height(30.sdp))

            ButtonRadius10(
                modifier = Modifier.fillMaxWidth(),
                text = "입력완료",
                backgroundColor = keyColor1,
                textColor = Color.White
            ) {
                navController.popBackStack()
                navController.navigate(EggMoneynaDestination.MAIN_CHILD)
            }
        }
    }
}