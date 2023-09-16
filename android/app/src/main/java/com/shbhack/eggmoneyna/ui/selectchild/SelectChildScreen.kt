package com.shbhack.eggmoneyna.ui.selectchild

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.data.local.AppPreferences
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination
import com.shbhack.eggmoneyna.ui.common.banner.ExplainBanner
import com.shbhack.eggmoneyna.ui.common.card.SelectChildCard
import com.shbhack.eggmoneyna.ui.selectchild.viewmodel.SelectChildViewModel
import com.shbhack.eggmoneyna.ui.theme.bannerPurple
import com.shbhack.eggmoneyna.ui.theme.contextTextColor
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun SelectChildScreen(navController: NavController, selectChildViewModel: SelectChildViewModel = hiltViewModel()) {

    Log.d("부모 토큰", "SelectChildScreen: ${AppPreferences.getToken()}")
    val list by selectChildViewModel.myChildren.collectAsState()
    selectChildViewModel.getAllUnActivatedChild()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color.White)
        ) {
            ExplainBanner(
                color = bannerPurple,
                title = stringResource(id = R.string.selectChildBannerTitle),
                content = stringResource(id = R.string.selectChildBannerContent),
                imgId = R.drawable.banner_image_purple,
                description = "신한은행 라쿤 캐릭터 플리 아이콘"
            )

            Spacer(modifier = Modifier.height(30.sdp))

            Text(
                modifier = Modifier.padding(horizontal = 15.sdp),
                text = stringResource(id = R.string.selectChildListTitle),
                style = TextStyle(
                    fontSize = 14.ssp,
                    fontWeight = FontWeight.SemiBold,
                )
            )
            Spacer(modifier = Modifier.height(5.sdp))
            Text(
                modifier = Modifier.padding(horizontal = 15.sdp),
                text = stringResource(id = R.string.selectChildListContnet),
                style = TextStyle(
                    fontSize = 11.ssp,
                    fontWeight = FontWeight.Normal,
                    color = contextTextColor
                )
            )

            Spacer(modifier = Modifier.height(20.sdp))

//            val list = listOf<ChildItem>(ChildItem(), ChildItem("이기표", false, 7))
            val listState = rememberLazyListState()
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight(),
                state = listState
            ) {
                itemsIndexed(list) { index, child ->
                    Spacer(modifier = Modifier.height(10.sdp))
                    if (child.gender) {
                        SelectChildCard(
                            imgId = R.drawable.icon_girl,
                            description = "여자 아이 아이콘",
                            name = child.childName,
                            info = "만 ${child.age}세 여아"
                        ) {
                            selectChildViewModel.activatingChild(child.id)
                            navController.navigate(EggMoneynaDestination.MAIN_PARENT)
                        }
                    } else {
                        SelectChildCard(
                            imgId = R.drawable.icon_boy,
                            description = "남자 아이 아이콘",
                            name = child.childName,
                            info = "만 ${child.age}세 남아"
                        ) {
                            selectChildViewModel.activatingChild(child.id)
                            navController.navigate(EggMoneynaDestination.MAIN_PARENT)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.sdp))
                }
            }
        }
    }
}