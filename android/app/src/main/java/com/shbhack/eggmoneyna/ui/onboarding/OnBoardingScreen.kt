package com.shbhack.eggmoneyna.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.shbhack.eggmoneyna.MainActivity
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(navController: NavController, activity: MainActivity) {

    Box(modifier = Modifier.fillMaxSize()) {
        val pageCount = 5
        val pageState = rememberPagerState()

        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pageState,
            count = pageCount
        ) { page ->
            when (page) {
                0 -> OnBoarding1Screen(navController, activity)
                1 -> OnBoarding2Screen(navController, activity)
                2 -> OnBoarding3Screen(navController, activity)
                3 -> OnBoarding4Screen(navController, activity)
                4 -> OnBoarding5Screen(navController, activity)
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(top = 70.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            HorizontalPagerIndicator(
                pagerState = pageState,
                activeColor = Color.Black.copy(alpha = 0.9f),
                inactiveColor = Color.Black.copy(alpha = 0.4f)
            )
        }
    }
}