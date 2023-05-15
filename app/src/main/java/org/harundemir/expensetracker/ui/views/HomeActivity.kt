@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package org.harundemir.expensetracker.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseScaffold() {
    val sheetState = SheetState(
        initialValue = SheetValue.Hidden,
        skipPartiallyExpanded = true,
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {}
        },
        sheetPeekHeight = 0.dp,
        topBar = {
            ExpenseAppBar()
        },
    ) { innerPadding ->
        ExpenseBody(
            modifier = Modifier.padding(innerPadding),
            scope = scope,
            sheetState = sheetState,
        )
    }
}

@ExperimentalMaterial3Api
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
fun ExpenseBody(modifier: Modifier = Modifier, scope: CoroutineScope, sheetState: SheetState) {
    ExpenseTrackerTheme {
        Surface(
            modifier = modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            Box {
                Surface(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        ExpenseInfo()
                        ExpenseList()
                    }
                }
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            if (!sheetState.isVisible) {
                                sheetState.expand()
                            } else {
                                sheetState.hide()
                            }
                        }
                    },
                    containerColor = Color(0xFF00BCD4),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
                }
            }
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
    val valueColor = if (!expense.isExpense) Color.Green else Color.Red
    Surface(modifier = Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Row {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier.background(
                        color = Color(0xFF00BCD4), shape = CircleShape
                    )
                ) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = "Expense Category Icon",
                        tint = Color.White,
                        modifier = Modifier.padding(8.dp),
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column() {
                    Text(text = expense.title)
                    Text(text = expense.category, color = Color.Gray, fontSize = 12.sp)
                }
            }
            Text(
                text = "${if (!expense.isExpense) "+" else "-"}${expense.value}",
                color = valueColor,
            )
        }
    }
}