package com.shbhack.eggmoneyna.ui.eggmoneyna

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack
import com.shbhack.eggmoneyna.ui.theme.EggmoneynaPurple

@Composable
fun EggMoneynaScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopWithBack(
                navController = navController,
                title = stringResource(id = R.string.eggmoneyna_appbar_title)
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            ColorBackgroundWithText()
            CustomCalendar()

        }
    }
}

@Preview
@Composable
fun EggMoneynaScreenPreview() {
    EggMoneynaScreen(rememberNavController())
}