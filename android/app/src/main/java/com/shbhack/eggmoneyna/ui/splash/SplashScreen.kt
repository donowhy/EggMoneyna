package com.shbhack.eggmoneyna.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.MainActivity
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.data.local.AppPreferences
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination
import com.shbhack.eggmoneyna.ui.theme.logoColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun SplashScreen(navController: NavController, activity: MainActivity) {

    LaunchedEffect(Unit) {
        delay(1500)
        withContext(Dispatchers.Main) {
            navController.popBackStack()
            if (AppPreferences.isFirstShowed()) {
                activity.setStatusBarOrigin()
                if (AppPreferences.isParent()) {
                    navController.navigate(EggMoneynaDestination.MAIN_PARENT)
                } else {
                    navController.navigate(EggMoneynaDestination.MAIN_CHILD)
                }
            } else {
                AppPreferences.checkFirstShowed()
                navController.navigate(EggMoneynaDestination.ON_BOARDING)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = logoColor)
            .statusBarsPadding()
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(
            modifier = Modifier
                .height(0.dp)
                .weight(3f)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.dp)
                .weight(2f)
        ) {
            Spacer(
                modifier = Modifier
                    .width(0.dp)
                    .weight(1f)
            )
            Image(
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = "알록달록 로고",
                modifier = Modifier
                    .width(0.dp)
                    .weight(1f)
            )
            Spacer(
                modifier = Modifier
                    .width(0.dp)
                    .weight(1f)
            )
        }

        Spacer(
            modifier = Modifier
                .height(0.dp)
                .weight(3f)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.dp)
                .weight(0.25f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.padding(end = 5.dp),
                painter = painterResource(id = R.drawable.shinhan_logo),
                contentDescription = "신한 로고"
            )
            Text(text = "신한은행", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(
            modifier = Modifier
                .height(0.dp)
                .weight(0.2f)
        )
    }
}