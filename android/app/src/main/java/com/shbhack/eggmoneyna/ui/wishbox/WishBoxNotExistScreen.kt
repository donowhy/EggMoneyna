package com.shbhack.eggmoneyna.ui.wishbox

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination
import com.shbhack.eggmoneyna.ui.common.button.ButtonRadius10
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack
import com.shbhack.eggmoneyna.ui.theme.keyColor1
import com.shbhack.eggmoneyna.ui.wishbox.views.WishBoxExplainBox
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishBoxNotExistScreen(navController: NavController) {

    var moneyText by remember { mutableStateOf(TextFieldValue("")) }
    var goodsText by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = {
            TopWithBack(
                navController = navController,
                title = "위시 박스"
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(15.sdp)
                .background(Color.White)
        ) {
            WishBoxExplainBox()

            Spacer(
                modifier = Modifier
                    .height(0.sdp)
                    .weight(1f)
            )

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth(0.1f)
                            .aspectRatio(1f),
                        painter = painterResource(id = R.drawable.icon_coin),
                        contentDescription = "목표 금액 아이콘"
                    )
                    Spacer(modifier = Modifier.width(20.sdp))
                    Text(
                        "목표 금액",
                        style = TextStyle(
                            fontSize = 13.ssp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.sdp))
                OutlinedTextField(
                    value = moneyText,
                    onValueChange = { moneyText = it },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 13.ssp),
                    placeholder = {
                        Text(
                            stringResource(R.string.wishBoxMoneyHintText),
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
            }

            Spacer(
                modifier = Modifier
                    .height(0.sdp)
                    .weight(1f)
            )

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth(0.1f)
                            .aspectRatio(1f),
                        painter = painterResource(id = R.drawable.icon_box),
                        contentDescription = "목표 상품 아이콘"
                    )
                    Spacer(modifier = Modifier.width(20.sdp))
                    Text(
                        "목표 상품",
                        style = TextStyle(
                            fontSize = 13.ssp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.sdp))
                OutlinedTextField(
                    value = goodsText,
                    onValueChange = { moneyText = it },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 13.ssp),
                    placeholder = {
                        Text(
                            stringResource(R.string.wishBoxGoodsHintText),
                            style = TextStyle(fontSize = 13.ssp)
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = keyColor1,
                    )
                )

            }

            Spacer(
                modifier = Modifier
                    .height(0.sdp)
                    .weight(3f)
            )

            ButtonRadius10(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.wishBoxButtonText),
                backgroundColor = keyColor1,
                textColor = Color.White
            ) {
                //위시 박스 생성
                navController.navigate(EggMoneynaDestination.MAIN_CHILD)
            }
        }
    }
}