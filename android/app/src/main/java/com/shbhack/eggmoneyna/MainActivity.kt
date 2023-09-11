package com.shbhack.eggmoneyna

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
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