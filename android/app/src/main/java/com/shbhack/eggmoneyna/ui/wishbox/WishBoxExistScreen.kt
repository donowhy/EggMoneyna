package com.shbhack.eggmoneyna.ui.wishbox

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.data.model.WishBoxInfoResponseDto
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination
import com.shbhack.eggmoneyna.ui.common.button.ButtonRadius10
import com.shbhack.eggmoneyna.ui.common.lottie.LottieLoader
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack
import com.shbhack.eggmoneyna.ui.theme.keyColor1
import com.shbhack.eggmoneyna.ui.theme.keyColorLight1
import com.shbhack.eggmoneyna.ui.wishbox.viewmodel.WishBoxVewModel
import com.shbhack.eggmoneyna.ui.wishbox.views.WishInfo
import com.shbhack.eggmoneyna.util.DateUtils
import com.shbhack.eggmoneyna.util.MoneyUtils
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun WishBoxExistScreen(navController: NavController, wishBoxVewModel: WishBoxVewModel) {

    var wishInfo by remember {
        mutableStateOf(wishBoxVewModel.myWishInfo)
    }
    val myWishInfo by wishBoxVewModel.myWishInfo.collectAsState()

//    wishBoxVewModel.getWishInfo()



    LaunchedEffect(myWishInfo) {
        wishInfo = wishBoxVewModel.myWishInfo
    }

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
                .padding(it)
                .fillMaxSize()
                .background(color = keyColorLight1),
        ) {
            Column(
                modifier = Modifier
                    .height(0.sdp)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                WishInfo(goods = "아이패드", lastMoney = 1000000)
                LottieLoader(
                    source = R.raw.wishbox_gathering,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
                val date = 16
                val gatherMoney = 20000
                Text(
                    text = "0일 동안 총 0원을 모았어요!",
                    style = TextStyle(fontSize = 14.ssp, fontWeight = FontWeight.Bold)
                )
            }

            ButtonRadius10(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.sdp, bottom = 15.sdp, end = 15.sdp),
                text = "위시 박스 채우기",
                backgroundColor = keyColor1,
                textColor = Color.White
            ) {
                navController.popBackStack()
                navController.navigate(EggMoneynaDestination.WISH_BOX_SAVE_MONEY)
            }
        }
    }
}