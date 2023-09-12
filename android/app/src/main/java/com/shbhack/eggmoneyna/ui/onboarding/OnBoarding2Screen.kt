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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.MainActivity
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination
import com.shbhack.eggmoneyna.ui.common.button.ButtonSkip
import com.shbhack.eggmoneyna.ui.common.lottie.LottieLoader
import com.shbhack.eggmoneyna.ui.theme.contextTextColor
import com.shbhack.eggmoneyna.ui.theme.onboardingColor2

@Composable
fun OnBoarding2Screen(navController: NavController, activity: MainActivity) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = onboardingColor2)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(top = 30.dp, start = 30.dp, end = 30.dp)
    ) {
        ButtonSkip(navController = navController, activity = activity)
        Spacer(
            modifier = Modifier
                .height(0.dp)
                .weight(1.5f)
        )
        Text(
            text = stringResource(id = R.string.onboarding2Title),
            fontWeight = FontWeight.Black,
            fontSize = 28.sp,
            modifier = Modifier.padding(bottom = 30.dp)
        )
        Text(
            text = stringResource(id = R.string.onboarding2Content),
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
            source = R.raw.onboarding_compliment,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
    }
}