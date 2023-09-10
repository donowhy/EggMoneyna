package com.shbhack.eggmoneyna.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shbhack.eggmoneyna.ui.theme.EggMoneynaTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EggMoneynaApp(
) {
    EggMoneynaTheme {
        val navController = rememberNavController()

        Box {
            EggMoneynaNavGraph(
                navController = navController
            )
        }

    }
}
