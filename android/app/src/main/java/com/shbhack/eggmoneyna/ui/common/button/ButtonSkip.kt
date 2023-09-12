package com.shbhack.eggmoneyna.ui.common.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.MainActivity
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination
@Composable
fun ButtonSkip(navController: NavController, activity: MainActivity) {
    ClickableText(
        text = AnnotatedString(text = stringResource(id = R.string.skip)),
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            textAlign = TextAlign.End
        ),
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            navController.popBackStack()
            navController.navigate(EggMoneynaDestination.CHOOSE_WHO)
            activity.setStatusBarOrigin()
        },
    )
}