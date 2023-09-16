package com.shbhack.eggmoneyna.ui.mainchild

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.data.local.AppPreferences
import com.shbhack.eggmoneyna.data.model.ChildHomeInfoResponseDto
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination
import com.shbhack.eggmoneyna.ui.common.card.coloredShadow
import com.shbhack.eggmoneyna.ui.common.lottie.LottieLoader
import com.shbhack.eggmoneyna.ui.common.util.noRippleClickable
import com.shbhack.eggmoneyna.ui.mainchild.viewmodel.MainChildViewModel
import com.shbhack.eggmoneyna.ui.mainchild.views.ChildUserInfo
import com.shbhack.eggmoneyna.ui.mainchild.views.MyAttendanceCard
import com.shbhack.eggmoneyna.ui.mainchild.views.MyComplimentCard
import com.shbhack.eggmoneyna.ui.mainchild.views.MyLastMoneyCard
import com.shbhack.eggmoneyna.ui.mainchild.views.MyLimitCard
import com.shbhack.eggmoneyna.ui.mainchild.views.MyWishBoxCard
import com.shbhack.eggmoneyna.ui.mainchild.views.TopIconRow
import com.shbhack.eggmoneyna.ui.theme.keyColorLight1
import com.shbhack.eggmoneyna.ui.wishbox.viewmodel.WishBoxVewModel
import com.shbhack.eggmoneyna.util.DateUtils
import com.shbhack.eggmoneyna.util.MoneyUtils
import ir.kaaveh.sdpcompose.sdp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainChildScreen(
    navController: NavController,
    mainChildViewModel: MainChildViewModel = hiltViewModel(),
    wishBoxVewModel: WishBoxVewModel
) {
    Log.d("토큰 확인", "MainChildScreen: ${AppPreferences.getToken()}")

    var homeInfo by remember { mutableStateOf(ChildHomeInfoResponseDto()) }
    val info by mainChildViewModel.mainInfo.collectAsState()
    mainChildViewModel.childHomeInfo()

    LaunchedEffect(info) {
        if (info.attemptDate != "") {
            homeInfo = info
            wishBoxVewModel.setWishAccountValue(1762120491470)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        val scrollState = rememberScrollState()
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(keyColorLight1)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(15.sdp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopIconRow(
                    alarmOnClick = { },
                    settingOnclick = { navController.navigate(EggMoneynaDestination.SETTING) }
                )
                ChildUserInfo(
                    homeInfo.childName,
                    DateUtils.calculateDdayOnlyNum(homeInfo.shinhanMongDate)
                )
                Box(modifier = Modifier.noRippleClickable {
                    navController.navigate(
                        EggMoneynaDestination.SHINHAN_MON
                    )
                }) {
                    LottieLoader(
                        source = R.raw.animation_egg,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .aspectRatio(1f)
                    )
                }
                MyLastMoneyCard(MoneyUtils.convertAddComma(homeInfo.balance) + "원") {
                    navController.navigate(EggMoneynaDestination.EGGMONEYNA)
                }
                Spacer(modifier = Modifier.height(16.sdp))
                MyLimitCard(
                    limit = MoneyUtils.convertAddComma(homeInfo.limitMoney) + "원",
                    last = MoneyUtils.convertAddComma(homeInfo.leftMoneyToLimit) + "원"
                )
                Spacer(modifier = Modifier.height(16.sdp))
                MyWishBoxCard {
                    navController.navigate(EggMoneynaDestination.WISH_BOX_EXIST)
                }
                Spacer(modifier = Modifier.height(16.sdp))

                Row {
                    Box(
                        modifier = Modifier
                            .width(0.sdp)
                            .weight(1f)
                            .aspectRatio(1.4f)
                            .coloredShadow(
                                color = Color.Black.copy(0.05f),
                                10.sdp,
                                8.sdp,
                                0.sdp,
                                0.sdp,
                                0.5f
                            )
                            .background(color = Color.White, shape = RoundedCornerShape(10.sdp))
                    ) {
                        MyAttendanceCard(date = homeInfo.attemptDate) {
                            // 출석
                        }
                    }
                    Spacer(modifier = Modifier.width(16.sdp))
                    Box(
                        modifier = Modifier
                            .width(0.sdp)
                            .weight(1f)
                            .aspectRatio(1.4f)
                            .coloredShadow(
                                color = Color.Black.copy(0.05f),
                                10.sdp,
                                8.sdp,
                                0.sdp,
                                0.sdp,
                                0.5f
                            )
                            .background(color = Color.White, shape = RoundedCornerShape(10.sdp))
                    ) {
                        MyComplimentCard(count = homeInfo.compliment)
                    }
                }
            }
        }
    }
}