package com.shbhack.eggmoneyna.util

import androidx.compose.ui.graphics.Color
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.shbhack.eggmoneyna.data.model.MonthGraphResponseDto
import com.shbhack.eggmoneyna.data.model.WeekGraphResponseDto
import com.shbhack.eggmoneyna.ui.expense.ExpenseCategory
import com.shbhack.eggmoneyna.ui.theme.PieChartColor1
import com.shbhack.eggmoneyna.ui.theme.PieChartColor2
import com.shbhack.eggmoneyna.ui.theme.PieChartColor3
import com.shbhack.eggmoneyna.ui.theme.PieChartColor4
import com.shbhack.eggmoneyna.ui.theme.PieChartColor5
import com.shbhack.eggmoneyna.ui.theme.PieChartColor6
import com.shbhack.eggmoneyna.ui.theme.PieChartColor7
import com.shbhack.eggmoneyna.util.DateUtils.getDayOfWeekFromDateString

object AnalysisUtils {
    fun convertCategoryColor(category: String): Color {
        val color = when (category) {
            "편의점" -> PieChartColor1
            "외식" -> PieChartColor2
            "카페" -> PieChartColor3
            "쇼핑" -> PieChartColor4
            "오락" -> PieChartColor5
            "입금" -> PieChartColor6
            else -> PieChartColor7

        }
        return color
    }

    fun parseToExpenseCategory(dto: MonthGraphResponseDto): List<ExpenseCategory> {
        val list = mutableListOf<ExpenseCategory>()

        if (dto.convenienceStore != 0.0) list.add(
            ExpenseCategory(
                "편의점",
                dto.convenienceStore.toFloat()
            )
        )
        if (dto.restaurants != 0.0) list.add(ExpenseCategory("외식", dto.restaurants.toFloat()))
        if (dto.game != 0.0) list.add(ExpenseCategory("오락", dto.game.toFloat()))
        if (dto.shopping != 0.0) list.add(ExpenseCategory("쇼핑", dto.shopping.toFloat()))
        if (dto.cafe != 0.0) list.add(ExpenseCategory("카페", dto.cafe.toFloat()))
        if (dto.etc != 0.0) list.add(ExpenseCategory("기타", dto.etc.toFloat()))

        return list
    }

    fun parseToLineChartData(data: List<WeekGraphResponseDto>): List<LineChartData> {
        val inputPoints = data.map {
            LineChartData.Point(it.totalInput.toFloat(), getDayOfWeekFromDateString(it.date))
        }

        val outputPoints = data.map {
            LineChartData.Point(it.totalOutput.toFloat(), getDayOfWeekFromDateString(it.date))
        }

        return listOf(
            LineChartData(
                points = inputPoints,
                startAtZero = true,
                lineDrawer = SolidLineDrawer(color = Color(0xFF76BAFF))
            ),
            LineChartData(
                points = outputPoints,
                startAtZero = true,
                padBy = 100f,
                lineDrawer = SolidLineDrawer(color = Color(0xFFFF96AF))
            )
        )
    }

}