package com.shbhack.eggmoneyna.ui.onboarding

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.util.CommonUtils

@Composable
fun OnBoardingScreen(navController: NavController) {
    CommonUtils.setSystemBarColor(color = Color.Transparent)
    Text("onBoarding 화면")
}