package com.shbhack.eggmoneyna.ui.mainparent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.EggMoneynaDestination
import com.shbhack.eggmoneyna.ui.common.button.ButtonRadius10
import com.shbhack.eggmoneyna.ui.common.card.ChildMoneyInfoCard
import com.shbhack.eggmoneyna.ui.common.card.GoodsItemCard
import com.shbhack.eggmoneyna.ui.common.util.noRippleClickable
import com.shbhack.eggmoneyna.ui.theme.keyColorLight1
import com.shbhack.eggmoneyna.ui.theme.logoColor
import com.shbhack.eggmoneyna.util.MoneyUtils
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun MainParentScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        val childList = listOf<ChildItem>(ChildItem(), ChildItem("이기표", false, 7))
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
                Row(
                    modifier = Modifier.padding(15.sdp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.mainParentTitle),
                        style = TextStyle(fontSize = 20.ssp, fontWeight = FontWeight.Bold)
                    )
                    Spacer(
                        modifier = Modifier
                            .width(0.sdp)
                            .weight(1f)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth(0.08f)
                                .aspectRatio(1f),
                            painter = painterResource(id = R.drawable.icon_alarm),
                            contentDescription = "알림 아이콘"
                        )
                        Spacer(modifier = Modifier.width(10.sdp))
                        Image(
                            modifier = Modifier
                                .fillMaxWidth(0.08f)
                                .aspectRatio(1f)
                                .noRippleClickable {
                                    navController.navigate(EggMoneynaDestination.SETTING)
                                },
                            painter = painterResource(id = R.drawable.icon_setting),
                            contentDescription = "세팅 아이콘"
                        )
                    }
                }
            }

            itemsIndexed(childList) { index, child ->
                Spacer(modifier = Modifier.height(10.sdp))
                ChildMoneyInfoCard(
                    name = child.name,
                    lastMoney = MoneyUtils.convertAddComma(child.money),
                ) {
                    navController.navigate(EggMoneynaDestination.EGGMONEYNA)
                }
                Spacer(modifier = Modifier.height(10.sdp))
                ButtonRadius10(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.sdp),
                    text = stringResource(id = R.string.mainParentButtonAnalysis),
                    backgroundColor = logoColor,
                    textColor = Color.White
                ) {
                    navController.navigate(EggMoneynaDestination.EXPENSE_ANALYSIS)
                }
            }


            item {
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

            itemsIndexed(goodsList) { index, goods ->
                Spacer(modifier = Modifier.height(15.sdp))
                GoodsItemCard(
                    imgId = goods.imgId,
                    goodsName = goods.name,
                    explainText = goods.explain
                ) {

                }
            }

            item {
                Spacer(modifier = Modifier.height(15.sdp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.sdp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.mainParentWarningTitle),
                            style = TextStyle(
                                fontSize = 12.ssp,
                                fontWeight = FontWeight.Bold,
                            )
                        )
                        Spacer(modifier = Modifier.height(10.sdp))
                        Text(
                            text = stringResource(id = R.string.mainParentWarningContent1),
                            style = TextStyle(
                                fontSize = 10.ssp,
                                fontWeight = FontWeight.Normal,
                            )
                        )
                        Spacer(modifier = Modifier.height(5.sdp))
                        Text(
                            text = stringResource(id = R.string.mainParentWarningContent2),
                            style = TextStyle(
                                fontSize = 10.ssp,
                                fontWeight = FontWeight.Normal,
                            )
                        )
                    }
                }
            }
        }
    }
}

data class ChildItem(
    var name: String = "강민승",
    var gender: Boolean = true,
    var age: Int = 8,
    var money: Int = 500000,
)

data class GoodsItem(
    var name: String = "신한 MY 주니어 통장",
    var explain: String = "우리 아이 첫 통장은",
    var imgId: Int = R.drawable.icon_account
)