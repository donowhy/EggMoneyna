package com.shbhack.eggmoneyna.ui.expense

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Locale.Category

class ExpenseViewModel : ViewModel() {
    // StateFlow to hold the list of email messages
    private val _categoryState = MutableStateFlow(emptyList<ExpenseCategory>())
    val categoryState: StateFlow<List<ExpenseCategory>> = _categoryState.asStateFlow()

    init {
        // Initialize the list of email messages
        // when the ViewModel is created
        _categoryState.update { getExpenseCategory() }
    }

    fun getExpenseCategory(): List<ExpenseCategory> {
        return listOf(
            ExpenseCategory(id = 1, type = "쇼핑", 0.32f),
            ExpenseCategory(id = 2, type = "음식점", 0.20f),
            ExpenseCategory(id = 3, type = "편의점", 0.16f),
            ExpenseCategory(id = 4, type = "여행", 0.2f),
            ExpenseCategory(id = 5, type = "문구", 0.07f),
            ExpenseCategory(id = 6, type = "기타", 0.05f)
        )
    }
}