package com.shbhack.eggmoneyna.ui.shinhanmong

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.common.animation.EggAnimationView
import com.shbhack.eggmoneyna.ui.common.button.RoundedCornerButton
import com.shbhack.eggmoneyna.ui.theme.keyColorLight1
import com.shbhack.eggmoneyna.ui.theme.keyColorLight2
import com.shbhack.eggmoneyna.ui.theme.logoColor
import ir.kaaveh.sdpcompose.sdp

@Composable
fun CharacterView(gif: Int, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(keyColorLight1)
    ) {
        EggAnimationView(gif)
        Surface(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.sdp),
            color = Color.Transparent
        ) {
            RoundedCornerButton(
                10,
                keyColorLight2,
                stringResource(id = R.string.shinhanmong_button_collection),
                16,
                logoColor
            ) {
                onClick()
            }
        }
    }

}
