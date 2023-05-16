package org.harundemir.expensetracker.ui.views.core.data

import androidx.compose.runtime.mutableStateListOf
import org.harundemir.expensetracker.ui.views.core.models.Expense

var expensesList: MutableList<Expense> = mutableStateListOf(
    Expense(title = "Soup", category = "Food-Drink", isExpense = true, value = 20.0),
    Expense(title = "Pixel 7", category = "Electronics", isExpense = true, value = 500.0),
    Expense(title = "Salary", category = "Income", isExpense = false, value = 3000.0),
    Expense(title = "Istanbul Ticket", category = "Travel", isExpense = true, value = 250.0),
    Expense(title = "Tea", category = "Food-Drink", isExpense = true, value = 5.0),
    Expense(title = "Utilities", category = "Bills", isExpense = true, value = 200.0),
    Expense(title = "Mobile App Project", category = "Income", isExpense = false, value = 1000.0),
    Expense(title = "Grocery", category = "Food-Drink", isExpense = true, value = 200.0),
    Expense(title = "Rent", category = "Accommodation", isExpense = true, value = 1000.0),
    Expense(title = "Internet", category = "Bills", isExpense = true, value = 20.0),
)