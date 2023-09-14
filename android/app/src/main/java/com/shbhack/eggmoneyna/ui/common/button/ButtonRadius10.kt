package com.shbhack.eggmoneyna.ui.common.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonRadius10(modifier: Modifier,text : String, backgroundColor: Color, textColor: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(10.dp)),
        colors = ButtonDefaults.buttonColors(backgroundColor, textColor)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
    }
}