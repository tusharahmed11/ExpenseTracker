package info.imtushar.expensetracker.screens.expenses

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import info.imtushar.expensetracker.R
import info.imtushar.expensetracker.components.common.SimpleToolbar
import info.imtushar.expensetracker.components.expnses.ExpenseItem
import info.imtushar.expensetracker.ui.theme.BackgroundElevated
import info.imtushar.expensetracker.ui.theme.BorderColor
import info.imtushar.expensetracker.ui.theme.LabelSecondary
import info.imtushar.expensetracker.ui.theme.SystemGray04
import info.imtushar.expensetracker.ui.theme.largeTitle
import info.imtushar.expensetracker.ui.theme.regularBody
import info.imtushar.expensetracker.ui.theme.regularHeadline
import info.imtushar.expensetracker.ui.theme.regularTitle2
import info.imtushar.expensetracker.utils.FilterType
import info.imtushar.expensetracker.utils.Recurrence
import info.imtushar.expensetracker.utils.toReadableFilter
import info.imtushar.expensetracker.utils.toTitleCase
import info.imtushar.expensetracker.viewmodel.ExpenseViewModel

@Composable
fun ExpensesScreen(viewModel: ExpenseViewModel = hiltViewModel()) {
    val isFilterDropDownExpanded = remember { mutableStateOf(false) }
    val groupedExpenses by viewModel.groupedExpenses.collectAsState(initial = emptyMap())
    val totalAmount by viewModel.totalAmount.collectAsState(initial = 0.0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        SimpleToolbar(title = "Expenses")

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 32.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Total for:", color = Color.White, style = regularBody)

                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .clickable(onClick = { isFilterDropDownExpanded.value = true })
                            .background(
                                color = BackgroundElevated,
                                shape = RoundedCornerShape(size = 6.dp)
                            )
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = viewModel.selectedFilter.name.toReadableFilter().lowercase(),
                                color = Color.White,
                                style = regularTitle2,
                                modifier = Modifier.padding(end = 10.dp)
                            )

                            DropdownMenu(
                                expanded = isFilterDropDownExpanded.value,
                                onDismissRequest = { isFilterDropDownExpanded.value = false },
                            ) {
                                FilterType.entries.forEach { filter ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = filter.name.toReadableFilter(),
                                                color = Color.White,
                                                style = regularBody
                                            )
                                        },
                                        onClick = {
                                            viewModel.selectedFilter = filter
                                            isFilterDropDownExpanded.value = false
                                        }
                                    )
                                }
                            }

                            Icon(
                                painter = painterResource(id = R.drawable.up_down_icon),
                                contentDescription = "up down icon",
                                tint = LabelSecondary
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "$",
                        color = LabelSecondary,
                        style = regularTitle2,
                        modifier = Modifier.padding(end = 4.dp)
                    )

                    Text(
                        text = String.format("%.2f", totalAmount),
                        color = Color.White,
                        style = largeTitle
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            groupedExpenses.forEach { (header, data) ->
                val (expenses, total) = data

                // Header
                item {
                    Text(
                        text = header,
                        color = LabelSecondary,
                        style = regularHeadline,
                        modifier = Modifier.padding(top = 16.dp, bottom = 10.dp)
                    )
                    HorizontalDivider(
                        thickness = 2.dp,
                        color = BorderColor,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                // Expense Items
                items(expenses) { expense ->
                    ExpenseItem(expense = expense)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Footer
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "Total: $${String.format("%.2f", total)}",
                            color = Color.White,
                            style = regularHeadline
                        )
                    }
                    HorizontalDivider(
                        thickness = 2.dp,
                        color = BorderColor,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
private fun ExpensesScreenPrev() {
    ExpensesScreen()
}