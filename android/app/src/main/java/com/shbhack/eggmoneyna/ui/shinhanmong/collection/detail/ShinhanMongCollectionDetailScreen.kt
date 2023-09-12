package com.shbhack.eggmoneyna.ui.shinhanmong.collection.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ShinhanMongCollectionDetailScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopWithBack(
                navController = navController,
                title = stringResource(id = R.string.shinhanmong_collection_appbar_title)
            )
        }
    ) { paddingValues ->
        ShinhanMongCollectionDetailView(paddingValues)
    }
}

