package com.shbhack.eggmoneyna.ui.expense

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.NoPointDrawer
import com.github.tehras.charts.line.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.line.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.common.component.ColorBackgroundWithText
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack
import com.shbhack.eggmoneyna.ui.theme.ExpenseAnalysisPink
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import java.text.NumberFormat

@Composable
fun ExpenseAnalysisScreen(navController: NavController) {

    // 1f -> 1000원
    val lineChartData1 = listOf<LineChartData>(
        LineChartData(
            points = listOf(
                LineChartData.Point(5f, "월"),
                LineChartData.Point(8f, "화"),
                LineChartData.Point(0f, "수"),
                LineChartData.Point(0f, "목"),
                LineChartData.Point(0.5f, "금"),
                LineChartData.Point(50f, "토"),
                LineChartData.Point(1.5f, "일")
            ),
            startAtZero = true,
            lineDrawer = SolidLineDrawer(color = Color.Red)
        ),
        LineChartData(
            points = listOf(
                LineChartData.Point(0f, "월"),
                LineChartData.Point(0f, "화"),
                LineChartData.Point(100f, "수"),
                LineChartData.Point(0f, "목"),
                LineChartData.Point(5f, "금"),
                LineChartData.Point(0f, "토"),
                LineChartData.Point(50f, "일")
            ),
            startAtZero = true,
            padBy = 100f,
            lineDrawer = SolidLineDrawer(color = Color.Blue)
        )
    )


    Scaffold(
        topBar = {
            TopWithBack(
                navController = navController,
                title = stringResource(id = R.string.expense_analysis_appbar_title)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            ColorBackgroundWithText(
                color = ExpenseAnalysisPink,
                title = stringResource(id = R.string.expense_analysis_month_expense),
                content = "150,800원"
            )
            Spacer(modifier = Modifier.size(12.sdp))
            Text(
                text = "이 주의 그래프",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.Black
                )

            )
            Spacer(modifier = Modifier.size(12.sdp))
            Divider()
            Spacer(modifier = Modifier.size(12.sdp))
            LineChartView(lineChartData1)
        }
    }
}

@Composable
fun LineChartView(lineChartData: List<LineChartData>) {
    val formatter = NumberFormat.getNumberInstance()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.sdp, end = 32.sdp)
    ) {
        LineChart(
            modifier = Modifier.height(200.sdp),
            linesChartData = lineChartData,
            pointDrawer = NoPointDrawer,
            animation = simpleChartAnimation(),
            xAxisDrawer = SimpleXAxisDrawer(labelTextSize = 12.ssp),
            yAxisDrawer = SimpleYAxisDrawer(labelTextSize = 12.ssp,
                labelRatio = 10,
                labelValueFormatter = { value -> formatter.format((value * 1000).toLong()) }),
            horizontalOffset = 10f
        )
    }
}


@Preview
@Composable
fun ExpenseAnalysisScreenPreivew() {
    ExpenseAnalysisScreen(navController = rememberNavController())
}