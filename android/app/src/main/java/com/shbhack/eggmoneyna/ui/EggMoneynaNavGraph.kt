package com.shbhack.eggmoneyna.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shbhack.eggmoneyna.ui.choosewho.ChooseWhoScreen
import com.shbhack.eggmoneyna.ui.onboarding.OnBoarding2Screen
import com.shbhack.eggmoneyna.ui.onboarding.OnBoarding3Screen
import com.shbhack.eggmoneyna.ui.onboarding.OnBoarding4Screen
import com.shbhack.eggmoneyna.ui.onboarding.OnBoarding5Screen
import com.shbhack.eggmoneyna.ui.eggmoneyna.EggMoneynaScreen
import com.shbhack.eggmoneyna.ui.onboarding.OnBoardingScreen
import com.shbhack.eggmoneyna.ui.splash.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EggMoneynaNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = EggMoneynaDestination.SPLASH
//    startDestination: String = EggMoneynaDestination.EGGMONEYNA
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(EggMoneynaDestination.SPLASH) {
            SplashScreen(navController)
        }
        composable(EggMoneynaDestination.EGGMONEYNA) {
            EggMoneynaScreen(navController)
        }
        composable(EggMoneynaDestination.ON_BOARDING) {
            OnBoardingScreen(navController)
        }
        composable(EggMoneynaDestination.ON_BOARDING2) {
            OnBoarding2Screen(navController)
        }
        composable(EggMoneynaDestination.ON_BOARDING3) {
            OnBoarding3Screen(navController)
        }
        composable(EggMoneynaDestination.ON_BOARDING4) {
            OnBoarding4Screen(navController)
        }
        composable(EggMoneynaDestination.ON_BOARDING5) {
            OnBoarding5Screen(navController)
        }
        composable(EggMoneynaDestination.CHOOSE_WHO) {
            ChooseWhoScreen(navController)
        }
    }
}