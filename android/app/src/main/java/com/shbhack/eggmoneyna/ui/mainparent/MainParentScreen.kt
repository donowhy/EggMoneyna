package com.shbhack.eggmoneyna.ui.mainparent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination
import com.shbhack.eggmoneyna.ui.common.card.ChildMoneyInfoCard
import com.shbhack.eggmoneyna.ui.common.card.GoodsItemCard
import com.shbhack.eggmoneyna.ui.mainparent.view.MainParentTop
import com.shbhack.eggmoneyna.ui.mainparent.view.RecommendListTitle
import com.shbhack.eggmoneyna.ui.mainparent.view.WarningBottom
import com.shbhack.eggmoneyna.ui.mainparent.viewmodel.MainParentViewModel
import com.shbhack.eggmoneyna.ui.theme.keyColorLight1
import com.shbhack.eggmoneyna.util.MoneyUtils
import ir.kaaveh.sdpcompose.sdp

@Composable
fun MainParentScreen(navController: NavController, mainParentViewModel: MainParentViewModel = hiltViewModel()) {

    val childList by mainParentViewModel.myChildren.collectAsState()
    mainParentViewModel.myChildInfo()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        val goodsList = listOf<GoodsItem>(
            GoodsItem(),
            GoodsItem(
                stringResource(id = R.string.mainParentItemStockTitle),
                stringResource(id = R.string.mainParentItemStockContent),
                R.drawable.icon_stock
            ),
            GoodsItem(
                stringResource(id = R.string.mainParentItemWishboxTitle),
                stringResource(id = R.string.mainParentItemWishboxContent),
                R.drawable.icon_wishbox
            )
        )
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(keyColorLight1)
        ) {
            item {
                MainParentTop(
                    alarmOnClick = {  },
                    settingOnclick = { navController.navigate(EggMoneynaDestination.SETTING) },
                    addOnClick = { navController.navigate(EggMoneynaDestination.SELECT_CHILD) }
                )
            }

            itemsIndexed(childList) { index, child ->
                Spacer(modifier = Modifier.height(10.sdp))
                ChildMoneyInfoCard(
                    name = child.childName,
                    lastMoney = MoneyUtils.convertAddComma(child.balance) + "원",
                ) {
                    navController.navigate(EggMoneynaDestination.EGGMONEYNA)
                }
                Spacer(modifier = Modifier.height(10.sdp))
            }


            item {
                RecommendListTitle()
            }

            itemsIndexed(goodsList) { index, goods ->
                Spacer(modifier = Modifier.height(15.sdp))
                GoodsItemCard(
                    imgId = goods.imgId,
                    goodsName = goods.name,
                    explainText = goods.explain
                ) {
                    navController.navigate(EggMoneynaDestination.EXTERNAL_LINK)
                }
            }

            item {
                WarningBottom()
            }
        }
    }
}

data class GoodsItem(
    var name: String = "신한 MY 주니어 통장",
    var explain: String = "우리 아이 첫 통장은",
    var imgId: Int = R.drawable.icon_account
)