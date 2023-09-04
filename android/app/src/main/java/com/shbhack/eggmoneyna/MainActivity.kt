package com.shbhack.eggmoneyna

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shbhack.eggmoneyna.ui.EggMoneynaApp
import com.shbhack.eggmoneyna.ui.theme.EggMoneynaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EggMoneynaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    EggMoneynaApp()
                }
            }
        }
    }
}