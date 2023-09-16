package com.shbhack.eggmoneyna.ui.mainparent.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.shbhack.eggmoneyna.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun RecommendListTitle() {
    Column {
        Spacer(modifier = Modifier.height(40.sdp))
        Text(
            modifier = Modifier.padding(horizontal = 15.sdp),
            text = stringResource(id = R.string.mainParentListTitle),
            style = TextStyle(
                fontSize = 13.ssp,
                fontWeight = FontWeight.Bold,
            )
        )
    }
}