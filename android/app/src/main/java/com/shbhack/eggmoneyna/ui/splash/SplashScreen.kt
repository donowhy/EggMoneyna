package com.shbhack.eggmoneyna.ui.splash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun SplashScreen(navController: NavController) {
    Text(
        text = "Hello Splash!",
    )
}