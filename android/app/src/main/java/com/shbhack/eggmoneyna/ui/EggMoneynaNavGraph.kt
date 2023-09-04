package com.shbhack.eggmoneyna.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shbhack.eggmoneyna.App
import com.shbhack.eggmoneyna.ui.splash.SplashScreen

@Composable
fun EggMoneynaNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = EggMoneynaDestination.SPLASH
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(EggMoneynaDestination.SPLASH) {
            SplashScreen(navController)
        }
    }
}