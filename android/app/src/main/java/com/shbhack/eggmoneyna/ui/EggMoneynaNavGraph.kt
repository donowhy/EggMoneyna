package com.shbhack.eggmoneyna.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shbhack.eggmoneyna.MainActivity
import com.shbhack.eggmoneyna.ui.authuser.AuthUserCheckScreen
import com.shbhack.eggmoneyna.ui.authuser.AuthUserMainScreen
import com.shbhack.eggmoneyna.ui.authuser.AuthUserSend1WonScreen
import com.shbhack.eggmoneyna.ui.authuser.viewmodel.AuthUserViewModel
import com.shbhack.eggmoneyna.ui.choosewho.ChooseWhoScreen
import com.shbhack.eggmoneyna.ui.comment.CommentScreen
import com.shbhack.eggmoneyna.ui.comment.viewmodel.CommentViewModel
import com.shbhack.eggmoneyna.ui.eggmoneyna.EggMoneynaScreen
import com.shbhack.eggmoneyna.ui.expense.ExpenseAnalysisScreen
import com.shbhack.eggmoneyna.ui.mainchild.MainChildScreen
import com.shbhack.eggmoneyna.ui.mainparent.MainParentScreen
import com.shbhack.eggmoneyna.ui.onboarding.OnBoardingScreen
import com.shbhack.eggmoneyna.ui.selectchild.SelectChildScreen
import com.shbhack.eggmoneyna.ui.setting.SettingScreen
import com.shbhack.eggmoneyna.ui.shinhanmong.ShinhanMongMainScreen
import com.shbhack.eggmoneyna.ui.shinhanmong.collection.ShinhanMongCollectionScreen
import com.shbhack.eggmoneyna.ui.shinhanmong.collection.detail.ShinhanMongCollectionDetailScreen
import com.shbhack.eggmoneyna.ui.splash.SplashScreen
import com.shbhack.eggmoneyna.ui.wishbox.WishBoxExistScreen
import com.shbhack.eggmoneyna.ui.wishbox.WishBoxNotExistScreen
import com.shbhack.eggmoneyna.ui.wishbox.WishBoxSaveMoneyScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EggMoneynaNavGraph(
    activity: MainActivity,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),

//    startDestination: String = EggMoneynaDestination.SPLASH
    startDestination: String = EggMoneynaDestination.MAIN_CHILD
) {
    val commentViewModel: CommentViewModel = hiltViewModel()

    val authUserViewModel: AuthUserViewModel = hiltViewModel()

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
                        animationSpec = tween(360)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                        animationSpec = tween(360)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                        animationSpec = tween(360)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                        animationSpec = tween(360)
                    )
                },
                content = content
            )
        }

        defaultSlideTransitions(EggMoneynaDestination.EXTERNAL_LINK) {
            DisposableEffect(Unit) {
                val uri = Uri.parse("https://shinhanblog.com/759")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                activity.startActivity(intent)
                navController.navigateUp()
                onDispose { }
            }
        }
        defaultSlideTransitions(EggMoneynaDestination.SPLASH) {
            SplashScreen(navController, activity)
        }
        defaultSlideTransitions(EggMoneynaDestination.EGGMONEYNA) {
            EggMoneynaScreen(navController, commentViewModel = commentViewModel)
        }
        defaultSlideTransitions(EggMoneynaDestination.ON_BOARDING) {
            OnBoardingScreen(navController, activity)
        }
        defaultSlideTransitions(EggMoneynaDestination.CHOOSE_WHO) {
            ChooseWhoScreen(navController)
        }
        defaultSlideTransitions(EggMoneynaDestination.AUTH_USER_MAIN) {
            AuthUserMainScreen(navController, authUserViewModel)
        }
        defaultSlideTransitions(EggMoneynaDestination.AUTH_USER_SEND_1WON) {
            AuthUserSend1WonScreen(navController, authUserViewModel)
        }
        defaultSlideTransitions(EggMoneynaDestination.AUTH_USER_CHECK) {
            AuthUserCheckScreen(navController, authUserViewModel)
        }
        defaultSlideTransitions(EggMoneynaDestination.SELECT_CHILD) {
            SelectChildScreen(navController)
        }
        defaultSlideTransitions(EggMoneynaDestination.MAIN_CHILD) {
            MainChildScreen(navController)
        }
        defaultSlideTransitions(EggMoneynaDestination.MAIN_PARENT) {
            MainParentScreen(navController)
        }
        defaultSlideTransitions(EggMoneynaDestination.WISH_BOX_EXIST) {
            WishBoxExistScreen(navController)
        }
        defaultSlideTransitions(EggMoneynaDestination.WISH_BOX_N0T_EXIST) {
            WishBoxNotExistScreen(navController)
        }
        defaultSlideTransitions(EggMoneynaDestination.WISH_BOX_SAVE_MONEY) {
            WishBoxSaveMoneyScreen(navController)
        }
        defaultSlideTransitions(EggMoneynaDestination.SHINHAN_MON) {
            ShinhanMongMainScreen(navController)
        }
        defaultSlideTransitions(EggMoneynaDestination.SHINHAN_MON_COLLECTION) {
            ShinhanMongCollectionScreen(navController)
        }
        defaultSlideTransitions(EggMoneynaDestination.SHINHAN_MON_COLLECTION_DETAIL) {
            ShinhanMongCollectionDetailScreen(navController)
        }
        defaultSlideTransitions(EggMoneynaDestination.EXPENSE_ANALYSIS) {
            ExpenseAnalysisScreen(navController)
        }
        defaultSlideTransitions(EggMoneynaDestination.SETTING) {
            SettingScreen(navController)
        }
        defaultSlideTransitions(EggMoneynaDestination.EXPENSE_COMMENT) {
            CommentScreen(navController, commentViewModel)
        }
    }
}
