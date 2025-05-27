package info.imtushar.expensetracker.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import info.imtushar.expensetracker.components.common.SimpleToolbar

@Composable
fun AddScreen(modifier: Modifier = Modifier) {

    Column(modifier = Modifier.fillMaxSize()) {
        SimpleToolbar(title = "Add")
    }
}

@Preview(showBackground = true)
@Composable
private fun AddScreenPrev() {
    AddScreen()
}