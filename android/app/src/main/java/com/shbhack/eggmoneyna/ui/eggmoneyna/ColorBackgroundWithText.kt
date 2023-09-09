package com.shbhack.eggmoneyna.ui.eggmoneyna

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.theme.EggmoneynaPurple

@Composable
fun ColorBackgroundWithText() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = EggmoneynaPurple)
            .padding(32.dp)
    ) {
        Text(
            text = stringResource(id = R.string.eggmoneyna_rest_money_title), fontWeight = FontWeight.Medium, fontSize = 18.sp, color = Color.White
        )
        Spacer(
            modifier = Modifier.size(4.dp)
        )
        Text(
            text = "500,000원", fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp, color = Color.White
        )
    }
}