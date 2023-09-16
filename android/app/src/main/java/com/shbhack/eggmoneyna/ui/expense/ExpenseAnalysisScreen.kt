package com.shbhack.eggmoneyna.ui.expense

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.NoPointDrawer
import com.github.tehras.charts.line.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.line.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.PieChart
import com.github.tehras.charts.piechart.PieChartData
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import com.github.tehras.charts.piechart.renderer.SimpleSliceDrawer
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.ui.common.component.ColorBackgroundWithText
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack
import com.shbhack.eggmoneyna.ui.theme.ExpenseAnalysisPink
import com.shbhack.eggmoneyna.ui.theme.keyColorLight1
import com.shbhack.eggmoneyna.util.AnalysisUtils
import com.shbhack.eggmoneyna.util.AnalysisUtils.parseToExpenseCategory
import com.shbhack.eggmoneyna.util.AnalysisUtils.parseToLineChartData
import com.shbhack.eggmoneyna.util.DateUtils
import com.shbhack.eggmoneyna.util.MoneyUtils
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import java.text.NumberFormat

@Composable
fun ExpenseAnalysisScreen(
    navController: NavController,
    viewModel: ExpenseViewModel = hiltViewModel()
) {
    val monthGraph by viewModel.monthGraphState.collectAsState()
    val weekGraph by viewModel.weekGraphState.collectAsState()
    val totalMonthOutput by viewModel.totalMonthOutputState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getMonthGraph()
        viewModel.getWeekGraph()
        viewModel.getTotalMonthOutput(DateUtils.getCurrentDateInYearMonthFormat())
    }

    var pieChartData by remember {
        mutableStateOf(listOf<ExpenseCategory>())
    }
    var lineChartData by remember {
        mutableStateOf(listOf<LineChartData>())
    }

    LaunchedEffect(monthGraph) {
        pieChartData = parseToExpenseCategory(monthGraph)
    }

    LaunchedEffect(weekGraph) {
        lineChartData = parseToLineChartData(weekGraph)
    }


    // 1f -> 1000원
//    val lineChartData = listOf<LineChartData>(
//        // 수입
//        LineChartData(
//            points = listOf(
//                LineChartData.Point(5f, "월"),
//                LineChartData.Point(8f, "화"),
//                LineChartData.Point(0f, "수"),
//                LineChartData.Point(0f, "목"),
//                LineChartData.Point(0.5f, "금"),
//                LineChartData.Point(50f, "토"),
//                LineChartData.Point(1.5f, "일")
//            ),
//            startAtZero = true,
//            lineDrawer = SolidLineDrawer(color = Color(0xFFFF96AF))
//        ),
//        // 지출
//        LineChartData(
//            points = listOf(
//                LineChartData.Point(0f, "월"),
//                LineChartData.Point(0f, "화"),
//                LineChartData.Point(100f, "수"),
//                LineChartData.Point(0f, "목"),
//                LineChartData.Point(5f, "금"),
//                LineChartData.Point(0f, "토"),
//                LineChartData.Point(50f, "일")
//            ),
//            startAtZero = true,
//            padBy = 100f,
//            lineDrawer = SolidLineDrawer(color = Color(0xFF76BAFF))
//        )
//    )

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
                .verticalScroll(rememberScrollState())
        ) {
            ColorBackgroundWithText(
                color = ExpenseAnalysisPink,
                title = stringResource(id = R.string.expense_analysis_month_expense),
                content = "${MoneyUtils.convertAddComma(totalMonthOutput.totalMonthOutput)}원"
            )

            // pie chart
            Text(
                modifier = Modifier.padding(12.sdp),
                text = "이 달의 지출 카테고리",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.Black
                )

            )
            Divider(
                modifier = Modifier.padding(start = 12.sdp, end = 12.sdp),
                thickness = 1.sdp
            )
            Spacer(modifier = Modifier.size(12.sdp))
            PieChartView(pieChartData)
            Divider(
                modifier = Modifier.padding(top = 12.sdp),
                thickness = 12.sdp,
                color = keyColorLight1
            )

            // lineChart
            Text(
                modifier = Modifier.padding(12.sdp),
                text = "이 주의 그래프",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.Black
                )

            )
            Divider(
                modifier = Modifier.padding(start = 12.sdp, end = 12.sdp),
                thickness = 1.sdp
            )
            Spacer(modifier = Modifier.size(16.sdp))
            if (lineChartData.isNotEmpty()) {
                LineChartView(lineChartData)
            }

            Spacer(modifier = Modifier.size(12.sdp))
            Divider(
                modifier = Modifier.padding(top = 12.sdp),
                thickness = 12.sdp,
                color = keyColorLight1
            )
        }
    }
}

@Composable
fun LineChartView(lineChartData: List<LineChartData>) {
    val formatter = NumberFormat.getNumberInstance()

    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.sdp)
            .padding(start = 32.sdp, end = 32.sdp),
        linesChartData = lineChartData,
        pointDrawer = NoPointDrawer,
        animation = simpleChartAnimation(),
        xAxisDrawer = SimpleXAxisDrawer(labelTextSize = 12.ssp, axisLineColor = Color.Gray),
        yAxisDrawer = SimpleYAxisDrawer(
            labelTextSize = 12.ssp, axisLineColor = Color.Gray,
            labelValueFormatter = { value -> formatter.format(value.toInt()) }
        ),
        horizontalOffset = 10f
    )

}

@Composable
fun PieChartView(categoryList: List<ExpenseCategory>) {
    val slices = categoryList.map { category ->
        PieChartData.Slice(
            category.value, AnalysisUtils.convertCategoryColor(category.type)
        )
    }
    val pieChartData =
        PieChartData(
            slices = slices
        )
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        PieChart(
            modifier = Modifier.size(160.sdp),
            pieChartData = pieChartData,
            sliceDrawer = SimpleSliceDrawer(sliceThickness = 52f)
        )
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            for (idx in categoryList.indices) {
                CategoryView(slices[idx], categoryList[idx].type)
            }
        }
    }
}

@Composable
fun CategoryView(item: PieChartData.Slice, type: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Surface(
            color = item.color,
            shape = RoundedCornerShape(12.sdp),
            modifier = Modifier
                .width(52.sdp)
                .padding(4.sdp)
        ) {
            Text(
                modifier = Modifier.padding(
                    start = 8.sdp,
                    end = 8.sdp,
                    top = 2.sdp,
                    bottom = 2.sdp
                ),
                text = type,
                style = TextStyle(
                    fontSize = 10.ssp,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = "${item.value}%",
            style = TextStyle(
                fontSize = 10.ssp,
                fontWeight = FontWeight.Normal
            ),
        )
    }
}


@Preview
@Composable
fun ExpenseAnalysisScreenPreivew() {
    ExpenseAnalysisScreen(navController = rememberNavController())
}

data class ExpenseCategory(
    val type: String,
    val value: Float
)