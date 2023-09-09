package com.shbhack.eggmoneyna.ui.shinhanmong

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.common.animation.EggAnimationView
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack
import com.shbhack.eggmoneyna.ui.theme.EggmoneynaOrange
import com.shbhack.eggmoneyna.ui.theme.keyColorLight1
import com.shbhack.eggmoneyna.ui.theme.keyColorLight2

@Composable
fun ShinhanMongMainScreen(navController: NavController) {
    Scaffold(
        topBar = {
        TopWithBack(
            navController = navController,
            title = stringResource(id = R.string.shinhanmong_appbar_title)
        )
    }) {
        Column(modifier = Modifier.padding(it)){
            CharacterView()
        }
    }
}

@Preview
@Composable
fun ShinhanMongMainScreenPreview() {
    ShinhanMongMainScreen(navController = rememberNavController())
}