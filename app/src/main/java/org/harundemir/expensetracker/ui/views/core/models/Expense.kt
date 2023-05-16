package org.harundemir.expensetracker.ui.views.core.models

data class Expense(
    var title: String,
    var category: String,
    var isExpense: Boolean,
    var value: Double,
)
