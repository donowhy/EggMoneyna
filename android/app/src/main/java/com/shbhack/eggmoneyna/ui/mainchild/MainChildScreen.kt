package com.shbhack.eggmoneyna.ui.mainchild

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.data.local.AppPreferences
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination
import com.shbhack.eggmoneyna.ui.common.card.coloredShadow
import com.shbhack.eggmoneyna.ui.common.lottie.LottieLoader
import com.shbhack.eggmoneyna.ui.common.util.noRippleClickable
import com.shbhack.eggmoneyna.ui.mainchild.views.ChildUserInfo
import com.shbhack.eggmoneyna.ui.mainchild.views.MyAttendanceCard
import com.shbhack.eggmoneyna.ui.mainchild.views.MyComplimentCard
import com.shbhack.eggmoneyna.ui.mainchild.views.MyLastMoneyCard
import com.shbhack.eggmoneyna.ui.mainchild.views.MyLimitCard
import com.shbhack.eggmoneyna.ui.mainchild.views.MyWishBoxCard
import com.shbhack.eggmoneyna.ui.mainchild.views.TopIconRow
import com.shbhack.eggmoneyna.ui.theme.keyColorLight1
import ir.kaaveh.sdpcompose.sdp

@Composable
fun MainChildScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        LaunchedEffect(Unit) {
            val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiLquYDsp4TsmIEyIiwiaWF0IjoxNjk0ODMyNTA5LCJleHAiOjE2OTU0MzczMDl9.kku4sw82Qt-OuURr-9j2WxGGdqeDgd4NtIsDsymNw2c"
            AppPreferences.initToken(token)
        }
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
                ChildUserInfo()
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
                MyLastMoneyCard("500,000원") {
                    navController.navigate(EggMoneynaDestination.EGGMONEYNA)
                }
                Spacer(modifier = Modifier.height(16.sdp))
                MyLimitCard(limit = "50,000원", last = "10,000원")
                Spacer(modifier = Modifier.height(16.sdp))
                MyWishBoxCard {
                    navController.navigate(EggMoneynaDestination.WISH_BOX)
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
                        MyAttendanceCard(date = 13) {
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
                        MyComplimentCard(count = 11)
                    }
                }
            }
        }
    }
}