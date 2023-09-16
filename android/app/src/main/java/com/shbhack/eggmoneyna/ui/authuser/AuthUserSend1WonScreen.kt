package com.shbhack.eggmoneyna.ui.authuser

import android.annotation.SuppressLint
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination
import com.shbhack.eggmoneyna.ui.authuser.viewmodel.AuthUserViewModel
import com.shbhack.eggmoneyna.ui.common.lottie.LottieLoader
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AuthUserSend1WonScreen(navController: NavController, authUserViewModel: AuthUserViewModel) {

    val accountNum by authUserViewModel.account.collectAsState()

    LaunchedEffect(Unit) {
        delay(3000)
        withContext(Dispatchers.Main) {
            navController.popBackStack()
            navController.navigate(EggMoneynaDestination.AUTH_USER_CHECK)
        }
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
                text = stringResource(id = R.string.authUserSend1WonExplain),
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

            Spacer(modifier = Modifier.height(40.sdp))
            LottieLoader(
                source = R.raw.auth_user_sending,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(bottom = 20.dp),
            )
        }
    }
}