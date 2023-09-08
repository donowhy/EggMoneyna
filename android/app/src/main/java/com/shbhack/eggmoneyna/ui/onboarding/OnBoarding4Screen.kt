package com.shbhack.eggmoneyna.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination
import com.shbhack.eggmoneyna.ui.theme.contextTextColor
import com.shbhack.eggmoneyna.ui.theme.onboardingColor4
import com.shbhack.eggmoneyna.util.CommonUtils

@Composable
fun OnBoarding4Screen(navController: NavController) {

    CommonUtils.setSystemBarColor(color = onboardingColor4)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = onboardingColor4)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ClickableText(
            text = AnnotatedString(text = stringResource(id = R.string.skip)),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                textAlign = TextAlign.End
            ),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(EggMoneynaDestination.CHOOSE_WHO)
            },
        )
        Spacer(
            modifier = Modifier
                .height(0.dp)
                .weight(1f)
        )
        CommonUtils.LottieLoader(
            source = R.raw.onboarding_wishbox,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(top = 20.dp)
        )
        Spacer(
            modifier = Modifier
                .height(0.dp)
                .weight(0.5f)
        )
        Text(
            text = stringResource(id = R.string.onboarding4Title),
            fontWeight = FontWeight.Black,
            fontSize = 28.sp,
            modifier = Modifier.padding(bottom = 30.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.onboarding4Content),
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            color = contextTextColor,
            textAlign = TextAlign.Center
        )
        Spacer(
            modifier = Modifier
                .height(0.dp)
                .weight(1f)
        )

        CommonUtils.buttonRadius40(text = stringResource(id = R.string.next), color = Color.Black) {
            navController.navigate(EggMoneynaDestination.ON_BOARDING5)
        }
    }
}