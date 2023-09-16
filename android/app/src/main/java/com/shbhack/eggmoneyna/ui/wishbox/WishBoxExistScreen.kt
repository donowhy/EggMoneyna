package com.shbhack.eggmoneyna.ui.wishbox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.common.lottie.LottieLoader
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack
import com.shbhack.eggmoneyna.ui.theme.secondaryColorLight1
import com.shbhack.eggmoneyna.ui.wishbox.views.WishInfo
import com.shbhack.eggmoneyna.util.MoneyUtils
import ir.kaaveh.sdpcompose.ssp

@Composable
fun WishBoxExistScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
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
                .background(color = secondaryColorLight1),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            WishInfo(goods = "애플워치", lastMoney = 250000)
            LottieLoader(
                source = R.raw.wishbox_gathering,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
            val date = 16
            val gatherMoney = 20000
            Text(
                text = "${date}일 동안 총 ${MoneyUtils.convertAddComma(gatherMoney)}원을 모았어요!",
                style = TextStyle(fontSize = 14.ssp, fontWeight = FontWeight.Bold)
            )
        }
    }
}