package com.shbhack.eggmoneyna.ui.choosewho

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.data.local.AppPreferences
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination
import com.shbhack.eggmoneyna.ui.common.banner.ExplainBanner
import com.shbhack.eggmoneyna.ui.common.card.ChooseWhoCard
import com.shbhack.eggmoneyna.ui.theme.bannerBlue
import com.shbhack.eggmoneyna.ui.theme.keyColorLight1
import ir.kaaveh.sdpcompose.sdp

@Composable
fun ChooseWhoScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(keyColorLight1)
    ) {
        ExplainBanner(
            color = bannerBlue,
            title = stringResource(id = R.string.chooseWhoBannerTitle),
            content = stringResource(id = R.string.chooseWhoBannerContent),
            imgId = R.drawable.banner_image_blue,
            description = "신한은행 아이콘"
        )

        Spacer(modifier = Modifier.height(30.sdp))

        ChooseWhoCard(
            imgId = R.drawable.icon_parent,
            description = "부모 아이콘",
            whoText = stringResource(id = R.string.chooseWhoParent),
            explainText = stringResource(id = R.string.chooseWhoParentExplain)
        ) {
            AppPreferences.checkSelectParent(true)
            navController.navigate(EggMoneynaDestination.AUTH_USER)
        }

        Spacer(modifier = Modifier.height(15.sdp))

        ChooseWhoCard(
            imgId = R.drawable.icon_children,
            description = "자녀 아이콘",
            whoText = stringResource(id = R.string.chooseWhoChild),
            explainText = stringResource(id = R.string.chooseWhoChildExplain)
        ) {
            AppPreferences.checkSelectParent(false)
            navController.navigate(EggMoneynaDestination.AUTH_USER)
        }
    }
}