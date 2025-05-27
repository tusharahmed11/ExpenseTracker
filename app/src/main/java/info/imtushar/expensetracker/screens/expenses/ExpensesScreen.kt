package info.imtushar.expensetracker.screens.expenses

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ExpensesScreen(modifier: Modifier = Modifier) {

    Box(modifier = modifier.fillMaxSize(),contentAlignment = Alignment.Center) {
        
        Text(text = "Expenses Screen", style = MaterialTheme.typography.headlineLarge)

    }
}

@Preview(showBackground = true)
@Composable
private fun ExpensesScreenPrev() {
    ExpensesScreen()
}