package info.imtushar.expensetracker.components.expnses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import info.imtushar.expensetracker.data.models.Category
import info.imtushar.expensetracker.data.models.Expense
import info.imtushar.expensetracker.ui.theme.LabelSecondary
import info.imtushar.expensetracker.ui.theme.regularHeadline
import info.imtushar.expensetracker.utils.Recurrence
import info.imtushar.expensetracker.utils.toColorInt
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

@Composable
fun ExpenseItem(
    expense: Expense
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = expense.title,
                color = Color.White,
                style = regularHeadline,
            )
            Text(
                text = "$ ${expense.amount}",
                color = Color.White,
                style = regularHeadline,
            )
        }

        Row(modifier = Modifier.fillMaxWidth().padding(top = 5.dp), horizontalArrangement = Arrangement.SpaceBetween) {

/*            Box(modifier = Modifier.background(color = Color(expense.category.color.toColorInt()), shape = RoundedCornerShape(size = 8.dp))){
                Text(
                    text = "",
                    color = Color.White,
                    style = regularHeadline,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }*/

            Text(
                text = "${expense.time.hour} : ${expense.time.minute}",
                color = LabelSecondary,
                style = regularHeadline,
            )

        }
    }
}

@Preview
@Composable
private fun ExpenseItemPrev() {
/*    ExpenseItem(expense = Expense(
        id = UUID.randomUUID(),
        title = "Pizza",
        amount = 660.0,
        category = Category(UUID.randomUUID(), "Groceries", "#4CAF50"),
        date = LocalDate.of(2025, 5, 27), // Today
        time = LocalTime.of(16,30),
        recurrence = Recurrence.NONE,
        note = "Milk + Eggs"
    ))*/
}