package info.imtushar.expensetracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import info.imtushar.expensetracker.R
import info.imtushar.expensetracker.components.common.SimpleToolbar
import info.imtushar.expensetracker.data.models.Category
import info.imtushar.expensetracker.ui.theme.BackgroundElevated
import info.imtushar.expensetracker.ui.theme.BackgroundSecondary
import info.imtushar.expensetracker.ui.theme.BorderColor
import info.imtushar.expensetracker.ui.theme.LabelSecondary
import info.imtushar.expensetracker.ui.theme.regularBody
import info.imtushar.expensetracker.utils.Recurrence
import info.imtushar.expensetracker.utils.toTitleCase
import info.imtushar.expensetracker.viewmodel.ExpenseViewModel

@Composable
fun AddScreen(modifier: Modifier = Modifier, viewModel: ExpenseViewModel = hiltViewModel()) {

    val categories by viewModel.categories.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        SimpleToolbar(title = "Add Expense")

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .background(color = BackgroundElevated, shape = RoundedCornerShape(size = 6.dp))
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                AmountField(viewModel)

                HorizontalDivider(thickness = 1.dp, color = BorderColor)

                RecurrenceField(viewModel)

                HorizontalDivider(thickness = 1.dp, color = BorderColor)

                DateField(viewModel)

                HorizontalDivider(thickness = 1.dp, color = BorderColor)

                NoteField(viewModel)

                HorizontalDivider(thickness = 1.dp, color = BorderColor)

                CategoryField(viewModel,categories)
            }
        }
    }
}

@Composable
private fun AmountField(viewModel: ExpenseViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 11.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Amount",
            color = Color.White,
            style = regularBody,
            modifier = Modifier.weight(1.6f)
        )

        Row(
            modifier = Modifier.weight(0.4f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = viewModel.amountValue,
                onValueChange = {
                    viewModel.amountValue = it
                },
                cursorBrush = SolidColor(Color.White),
                modifier = Modifier
                    .background(Color.Transparent),
                textStyle = regularBody.copy(color = Color.White),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (viewModel.amountValue.isEmpty()) {
                            Text(
                                text = "Amount",
                                color = LabelSecondary,
                                style = regularBody
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }
    }
}

@Composable
private fun DateField(viewModel: ExpenseViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 11.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Date",
            color = Color.White,
            style = regularBody,
        )

        Row(
            modifier = Modifier.background(color = BackgroundSecondary, shape = RoundedCornerShape(size = 6.dp)),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sep 16, 2022",
                style = regularBody,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 11.dp, vertical = 3.dp)
            )
        }
    }
}

@Composable
private fun RecurrenceField(viewModel: ExpenseViewModel) {

    val isDropDownExpanded = remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 11.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Recurrence",
            color = Color.White,
            style = regularBody,

        )

        Row(
            modifier = Modifier.clickable(
                onClick = {
                    isDropDownExpanded.value = true
                }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            val recurrenceText = if (viewModel.selectedRecurrence == null) "None" else viewModel.selectedRecurrence!!.name.toTitleCase()

            Text(
                text = recurrenceText,
                color = if (viewModel.selectedRecurrence == null) LabelSecondary else Color.White,
                style = regularBody,
                modifier = Modifier.padding(end = 10.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.up_down_icon),
                contentDescription = "up down icon",
                tint = LabelSecondary
            )


            DropdownMenu(
                expanded = isDropDownExpanded.value,
                onDismissRequest = {
                    isDropDownExpanded.value = false
                },
            ) {
                Recurrence.entries.forEach { recurrence ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = recurrence.name,
                                color = Color.White,
                                style = regularBody
                            )
                        },
                        onClick = {
                            viewModel.selectedRecurrence = recurrence
                            isDropDownExpanded.value = false
                        }
                    )
                }
            }
        }
    }


}

@Composable
private fun NoteField(viewModel: ExpenseViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 11.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Note",
            color = Color.White,
            style = regularBody,
            modifier = Modifier.weight(1f)
        )

        Row(
            modifier = Modifier.weight(1.3f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                onValueChange = {
                    viewModel.noteValue = it
                },
                value = viewModel.noteValue,
                modifier = Modifier
                    .background(Color.Transparent),
                textStyle = regularBody.copy(color = Color.White),
                minLines = 3,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = BackgroundSecondary,
                    unfocusedContainerColor = BackgroundSecondary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = {
                    Text(
                        text = "Add Note",
                        color = LabelSecondary,
                        style = regularBody
                    )
                },
                shape = RoundedCornerShape(6.dp)
            )
        }
    }
}

@Composable
private fun CategoryField(viewModel: ExpenseViewModel, categories: List<Category>) {

    val isDropDownExpanded = remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 11.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Recurrence",
            color = Color.White,
            style = regularBody,

            )

        Row(
            modifier = Modifier.clickable(
                onClick = {
                    isDropDownExpanded.value = true
                }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            val recurrenceText = if (viewModel.selectedRecurrence == null) "None" else viewModel.selectedRecurrence!!.name.toTitleCase()

            Text(
                text = recurrenceText,
                color = if (viewModel.selectedRecurrence == null) LabelSecondary else Color.White,
                style = regularBody,
                modifier = Modifier.padding(end = 10.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.up_down_icon),
                contentDescription = "up down icon",
                tint = LabelSecondary
            )


            DropdownMenu(
                expanded = isDropDownExpanded.value,
                onDismissRequest = {
                    isDropDownExpanded.value = false
                },
            ) {
                Recurrence.entries.forEach { recurrence ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = recurrence.name,
                                color = Color.White,
                                style = regularBody
                            )
                        },
                        onClick = {
                            viewModel.selectedRecurrence = recurrence
                            isDropDownExpanded.value = false
                        }
                    )
                }
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
private fun AddScreenPrev() {
    AddScreen()
}