@file:OptIn(ExperimentalMaterial3Api::class)

package org.harundemir.expensetracker.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.harundemir.expensetracker.ui.views.core.data.expensesList
import org.harundemir.expensetracker.ui.views.core.models.Expense
import org.harundemir.expensetracker.ui.views.ui.theme.ExpenseTrackerTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseScaffold()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeActivityPreview() {
    ExpenseScaffold()
}

@Composable
fun ExpenseScaffold() {
    Scaffold(
        topBar = {
            ExpenseAppBar()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Handle FAB click */ },
                containerColor = Color(0xFF00BCD4),
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
            }
        },
    ) { innerPadding ->
        ExpenseBody(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun ExpenseAppBar() {
    CenterAlignedTopAppBar(
        title = { Text(text = "Expense", color = Color.White) },
        colors = centerAlignedTopAppBarColors(
            containerColor = Color(0xFF00BCD4),
        )
    )
}

@Composable
fun ExpenseBody(modifier: Modifier = Modifier) {
    ExpenseTrackerTheme {
        Surface(
            modifier = modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            ExpenseBody()
        }
    }
}

@Composable
fun ExpenseBody() {
    Surface(modifier = Modifier.fillMaxWidth()) {
        Column {
            ExpenseInfo()
            ExpenseList()
        }
    }
}

@Composable
fun ExpenseInfo() {
    Surface(color = Color(0xFF00BCD4)) {
        Column {
            ExpenseInfoRow()
        }
    }
}

@Composable
fun ExpenseInfoRow() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
    ) {
        ExpenseInfoSection(title = "INCOME", value = 800.0)
        ExpenseInfoSection(title = "EXPENSE", value = 135.0)
        ExpenseInfoSection(title = "BALANCE", value = 675.0)
    }
}

@Composable
fun ExpenseInfoSection(title: String, value: Double) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "\$$value", color = Color.White)
        Text(text = title, color = Color.White, fontSize = 10.sp)
    }
}

@Composable
fun ExpenseList() {
    LazyColumn {
        items(expensesList) { item ->
            ExpenseListCard(item)
        }
    }
}

@Composable
fun ExpenseListCard(expense: Expense) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Row {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Expense Category Icon")
                Column() {
                    Text(text = expense.title)
                    Text(text = expense.category)
                }
            }
            Text(text = expense.value.toString())
        }
    }
}