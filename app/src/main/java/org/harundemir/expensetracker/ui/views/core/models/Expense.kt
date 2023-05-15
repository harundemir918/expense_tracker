package org.harundemir.expensetracker.ui.views.core.models

data class Expense(
    val title: String,
    val category: String,
    val isExpense: Boolean,
    val value: Double,
)
