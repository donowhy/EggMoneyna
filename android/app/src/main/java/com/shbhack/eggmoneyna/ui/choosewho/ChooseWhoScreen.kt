package com.shbhack.eggmoneyna.ui.choosewho

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.ui.theme.keyColorLight3
import com.shbhack.eggmoneyna.util.CommonUtils

@Composable
fun ChooseWhoScreen(navController: NavController) {
    CommonUtils.setSystemBarColor(color = keyColorLight3)
    Text("부모/자녀 선택 화면")
}