package com.shbhack.eggmoneyna.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shbhack.eggmoneyna.ui.choosewho.ChooseWhoScreen
import com.shbhack.eggmoneyna.ui.eggmoneyna.EggMoneynaScreen
import com.shbhack.eggmoneyna.ui.onboarding.OnBoardingScreen
import com.shbhack.eggmoneyna.ui.shinhanmong.ShinhanMongMainScreen
import com.shbhack.eggmoneyna.ui.shinhanmong.collection.ShinhanMongCollectionScreen
import com.shbhack.eggmoneyna.ui.splash.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EggMoneynaNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
//    startDestination: String = EggMoneynaDestination.SPLASH
    startDestination: String = EggMoneynaDestination.SHINHAN_MON
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        // 내비게이션 slide transitions
        fun NavGraphBuilder.defaultSlideTransitions(
            destination: String,
            content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
        ) {
            composable(
                destination,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                        animationSpec = tween(700)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                        animationSpec = tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                        animationSpec = tween(700)
                    )
                },
                content = content
            )
        }

        composable(EggMoneynaDestination.SPLASH) {
            SplashScreen(navController)
        }
        composable(EggMoneynaDestination.EGGMONEYNA) {
            EggMoneynaScreen(navController)
        }
        composable(EggMoneynaDestination.ON_BOARDING) {
            OnBoardingScreen(navController)
        }
        composable(EggMoneynaDestination.CHOOSE_WHO) {
            ChooseWhoScreen(navController)
        }

        defaultSlideTransitions(EggMoneynaDestination.SHINHAN_MON) {
            ShinhanMongMainScreen(navController)
        }

        defaultSlideTransitions(EggMoneynaDestination.SHINHAN_MON_COLLECTION) {
            ShinhanMongCollectionScreen(navController)
        }

    }
}
