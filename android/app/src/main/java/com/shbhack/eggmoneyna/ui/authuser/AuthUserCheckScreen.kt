package com.shbhack.eggmoneyna.ui.authuser

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.data.local.AppPreferences
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination
import com.shbhack.eggmoneyna.ui.authuser.viewmodel.AuthUserViewModel
import com.shbhack.eggmoneyna.ui.common.button.ButtonRadius10
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack
import com.shbhack.eggmoneyna.ui.theme.logoColor
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthUserCheckScreen(
    navController: NavController,
    authUserViewModel: AuthUserViewModel
) {
    val accountNum by authUserViewModel.account.collectAsState()
    val authNum by authUserViewModel.authNum.collectAsState()
    val activatedStatus by authUserViewModel.activated.collectAsState()
    val tokenState by authUserViewModel.tokenState.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    var text by remember { mutableStateOf(TextFieldValue(authNum.toString())) }

    val context = LocalContext.current

    LaunchedEffect(tokenState) {
        if (tokenState) {
            authUserViewModel.setAccountValue(0L)
            authUserViewModel.setAuthNumValue(0)
            authUserViewModel.setActivatedValue(false)
            authUserViewModel.setTokenState(false)

            if (AppPreferences.getToken() == "") {
                Toast.makeText(context, "본인 인증에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                navController.navigate(EggMoneynaDestination.CHOOSE_WHO)

            } else if (!AppPreferences.isParent() && !activatedStatus) {
                Toast.makeText(context, "계정이 등록되어 있지 않습니다.", Toast.LENGTH_SHORT).show()
                navController.navigate(EggMoneynaDestination.CHOOSE_WHO)

            } else {
                Toast.makeText(context, "본인 인증에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
//                AppPreferences.checkFirstShowed()

                if (AppPreferences.isParent()) {
                    navController.navigate(EggMoneynaDestination.SELECT_CHILD)
                } else {
                    navController.navigate(EggMoneynaDestination.MAIN_CHILD)
                }
            }
        }
    }

    LaunchedEffect(authNum) {
        text = TextFieldValue(authNum.toString())
    }

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
                text = stringResource(id = R.string.authUserCheckExplain),
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
                    text = "신한 ${accountNum}",
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 14.ssp)
                )
            }

            Spacer(modifier = Modifier.height(10.sdp))

            OutlinedTextField(
                value = text,
                onValueChange = { if (it.text.length <= 4) text = it },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = 13.ssp),
                placeholder = {
                    Text(
                        stringResource(R.string.authUserCheckEditText),
                        style = TextStyle(fontSize = 13.ssp)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = logoColor,
                ),

                )

            Spacer(modifier = Modifier.height(30.sdp))

            ButtonRadius10(
                modifier = Modifier.fillMaxWidth(),
                text = "확인하기",
                backgroundColor = logoColor,
                textColor = Color.White
            ) {
                coroutineScope.launch {
                    if (AppPreferences.isParent()) {
                        authUserViewModel.checkParentAuth()
                    } else {
                        authUserViewModel.checkChildAuth()
                    }
                }
            }
        }
    }
}