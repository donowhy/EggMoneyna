package com.shbhack.eggmoneyna.ui.authuser

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination
import com.shbhack.eggmoneyna.ui.authuser.viewmodel.AuthUserViewModel
import com.shbhack.eggmoneyna.ui.common.button.ButtonRadius10
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack
import com.shbhack.eggmoneyna.ui.theme.logoColor
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthUserMainScreen(
    navController: NavController,
    authUserViewModel: AuthUserViewModel
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopWithBack(
                navController = navController,
                title = ""
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(15.sdp)
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.authUserAccountExplain),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.ssp)
            )
            Spacer(modifier = Modifier.height(40.sdp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(0.08f)
                        .aspectRatio(1f),
                    painter = painterResource(id = R.drawable.shinhan_logo_blue),
                    contentDescription = "신한은행 로고"
                )
                Spacer(modifier = Modifier.width(5.sdp))
                Text(
                    text = "신한",
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 14.ssp)
                )
            }

            Spacer(modifier = Modifier.height(10.sdp))


            OutlinedTextField(
                value = text,
                onValueChange = { if (it.text.length <= 12) text = it },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = 13.ssp),
                placeholder = {
                    Text(
                        stringResource(R.string.authUserAccountEditText),
                        style = TextStyle(fontSize = 13.ssp)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = logoColor,
                )
            )

            Spacer(modifier = Modifier.height(30.sdp))

            ButtonRadius10(
                modifier = Modifier.fillMaxWidth(),
                text = "입력완료",
                backgroundColor = logoColor,
                textColor = Color.White
            ) {
                if (text.text == "") {
                    authUserViewModel.setAccountValue(0L)
                } else {
                    authUserViewModel.setAccountValue(text.text.toLong())
                }

                authUserViewModel.send1Won()
                navController.popBackStack()
                navController.navigate(EggMoneynaDestination.AUTH_USER_SEND_1WON)
            }
        }
    }
}