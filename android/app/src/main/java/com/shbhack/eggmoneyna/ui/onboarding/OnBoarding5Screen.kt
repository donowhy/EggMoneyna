package com.shbhack.eggmoneyna.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.MainActivity
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination
import com.shbhack.eggmoneyna.ui.common.button.ButtonRadius40
import com.shbhack.eggmoneyna.ui.common.lottie.LottieLoader
import com.shbhack.eggmoneyna.ui.theme.contextTextColor
import com.shbhack.eggmoneyna.ui.theme.onboardingColor5

@Composable
fun OnBoarding5Screen(navController: NavController, activity: MainActivity) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = onboardingColor5)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(30.dp)
    ) {
        Spacer(
            modifier = Modifier
                .height(0.dp)
                .weight(1.5f)
        )
        Text(
            text = stringResource(id = R.string.onboarding5Title),
            fontWeight = FontWeight.Black,
            fontSize = 28.sp,
            modifier = Modifier.padding(bottom = 30.dp)
        )
        Text(
            text = stringResource(id = R.string.onboarding5Content),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = contextTextColor
        )
        Spacer(
            modifier = Modifier
                .height(0.dp)
                .weight(1f)
        )
        LottieLoader(
            source = R.raw.onboarding_plan,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        ButtonRadius40(text = stringResource(id = R.string.start), color = Color.Black) {
            navController.popBackStack()
            navController.navigate(EggMoneynaDestination.CHOOSE_WHO)
//            activity.setStatusBarOrigin()
        }
    }
}