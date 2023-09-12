package com.shbhack.eggmoneyna.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.shbhack.eggmoneyna.MainActivity
import com.shbhack.eggmoneyna.ui.theme.EggMoneynaTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EggMoneynaApp( activity: MainActivity
) {
    EggMoneynaTheme {
        val navController = rememberNavController()

        Box {
            EggMoneynaNavGraph(
                activity= activity,
                navController = navController
            )
        }

    }
}
