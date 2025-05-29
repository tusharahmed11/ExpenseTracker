package info.imtushar.expensetracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import info.imtushar.expensetracker.components.common.AdvancedTimePicker
import info.imtushar.expensetracker.components.common.CustomToast
import info.imtushar.expensetracker.components.common.DatePickerModal
import info.imtushar.expensetracker.components.common.SimpleToolbar
import info.imtushar.expensetracker.data.AppConstants.dateFormatter
import info.imtushar.expensetracker.data.AppConstants.timeFormatter
import info.imtushar.expensetracker.data.models.Category
import info.imtushar.expensetracker.ui.theme.BackgroundElevated
import info.imtushar.expensetracker.ui.theme.BackgroundSecondary
import info.imtushar.expensetracker.ui.theme.BorderColor
import info.imtushar.expensetracker.ui.theme.Destructed
import info.imtushar.expensetracker.ui.theme.LabelSecondary
import info.imtushar.expensetracker.ui.theme.Primary
import info.imtushar.expensetracker.ui.theme.SuccessColor
import info.imtushar.expensetracker.ui.theme.regularBody
import info.imtushar.expensetracker.ui.theme.regularHeadline
import info.imtushar.expensetracker.utils.PastOrPresentSelectableDates
import info.imtushar.expensetracker.utils.Recurrence
import info.imtushar.expensetracker.utils.toTitleCase
import info.imtushar.expensetracker.viewmodel.ExpenseViewModel
import java.time.LocalDate
import java.time.LocalTime


@Composable
fun AddScreen( viewModel: ExpenseViewModel = hiltViewModel()) {

    var validationMessage by remember { mutableStateOf("") }
    var showToast by remember { mutableStateOf(false) }

    val categories by viewModel.categories.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        SimpleToolbar(title = "Add Expense")

        Column(
            modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 16.dp)
                    .background(color = BackgroundElevated, shape = RoundedCornerShape(size = 6.dp))
            ) {
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {

                    TitleField(viewModel)

                    HorizontalDivider(thickness = 1.dp, color = BorderColor)

                    AmountField(viewModel)

                    HorizontalDivider(thickness = 1.dp, color = BorderColor)

                    RecurrenceField(viewModel)

                    HorizontalDivider(thickness = 1.dp, color = BorderColor)

                    DateField(viewModel)

                    HorizontalDivider(thickness = 1.dp, color = BorderColor)

                    TimeField(viewModel)

                    HorizontalDivider(thickness = 1.dp, color = BorderColor)

                    NoteField(viewModel)

                    HorizontalDivider(thickness = 1.dp, color = BorderColor)

                    CategoryField(viewModel, categories)


                }
            }

            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 16.dp),
                onClick = {
                    viewModel.addExpense { validationResult ->

                        validationMessage = validationResult
                        showToast = true
                    }
                },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Primary
                ),
                contentPadding = PaddingValues(vertical = 13.dp, horizontal = 20.dp),
                shape = RoundedCornerShape(size = 10.dp),
            ) {
                Text("Submit expense", style = regularHeadline, color = Color.White)
            }
        }

    }

    if (showToast) {

        val successMessage = "Expense added successfully"

        CustomToast(
            message = validationMessage.ifEmpty { successMessage },
            backgroundColor = if (validationMessage.isEmpty()) SuccessColor else Destructed,
            onDismiss = { showToast = false }
        )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateField(viewModel: ExpenseViewModel) {

    val datePickerState = rememberDatePickerState(selectableDates = PastOrPresentSelectableDates)
    var showDateDialog by remember { mutableStateOf(false) }


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
            modifier = Modifier.background(
                color = BackgroundSecondary,
                shape = RoundedCornerShape(size = 6.dp)
            ),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = viewModel.selectedDate.format(dateFormatter),
                style = regularBody,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 11.dp, vertical = 3.dp)
                    .clickable(onClick = {
                        showDateDialog = true
                    })
            )
        }
    }

    // Show Calendar Dialog
    if (showDateDialog) {

        DatePickerModal(
            datePickerState = datePickerState,
            onDateSelected = {
            viewModel.selectedDate = LocalDate.ofEpochDay(it!! / (24 * 60 * 60 * 1000))
            showDateDialog = false
        }, onDismiss = { showDateDialog = false })

       /* AlertDialog(
            onDismissRequest = { showDateDialog = false },
            title = { Text(text = "Select Date") },
            text = {
                AndroidView(
                    factory = { context ->
                        CalendarView(context).apply {
                            // Set initial date to the selected date
                            val initialDate = viewModel.selectedDate
                            date = initialDate.toEpochDay() * 24 * 60 * 60 * 1000
                            //date = Instant.from(initialDate.atStartOfDay(ZoneId.systemDefault())).toEpochMilli()

                            // Handle date selection
                            setOnDateChangeListener { _, year, month, dayOfMonth ->
                                viewModel.selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            },
            confirmButton = {
                Button(onClick = { showDateDialog = false }) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = { showDateDialog = false }) {
                    Text("Cancel")
                }
            }
        )*/
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimeField(viewModel: ExpenseViewModel) {

    var showTimeDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 11.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Time",
            color = Color.White,
            style = regularBody,
        )

        Row(
            modifier = Modifier.background(
                color = BackgroundSecondary,
                shape = RoundedCornerShape(size = 6.dp)
            ),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = viewModel.selectedTime.format(timeFormatter),
                style = regularBody,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 11.dp, vertical = 3.dp)
                    .clickable(onClick = {
                        showTimeDialog = true
                    })
            )
        }
    }
    if (showTimeDialog){
        AdvancedTimePicker(
            onConfirm = {

                val hour = it.hour
                val min = it.minute

                viewModel.selectedTime = LocalTime.of(hour, min)

                showTimeDialog = false
            },
            onDismiss = { showTimeDialog = false }
        )
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

            val recurrenceText =
                if (viewModel.selectedRecurrence == null) "None" else viewModel.selectedRecurrence!!.name.toTitleCase()

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
                                text = recurrence.name.toTitleCase(),
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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
private fun TitleField(viewModel: ExpenseViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 11.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Title",
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
                    viewModel.titleValue = it
                },
                value = viewModel.titleValue,
                modifier = Modifier
                    .background(Color.Transparent),
                textStyle = regularBody.copy(color = Color.White),
                minLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = BackgroundSecondary,
                    unfocusedContainerColor = BackgroundSecondary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = {
                    Text(
                        text = "Add Title",
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

    val isCategoryDropDownExpanded = remember {
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
                    isCategoryDropDownExpanded.value = true
                }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            val categoryText =
                if (viewModel.selectedCategory == null) "Select Category" else viewModel.selectedCategory!!.name

            Text(
                text = categoryText,
                color = if (viewModel.selectedCategory == null) LabelSecondary else Color.White,
                style = regularBody,
                modifier = Modifier.padding(end = 10.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.up_down_icon),
                contentDescription = "up down icon",
                tint = LabelSecondary
            )


            DropdownMenu(
                expanded = isCategoryDropDownExpanded.value,
                onDismissRequest = {
                    isCategoryDropDownExpanded.value = false
                },
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = category.name,
                                color = Color.White,
                                style = regularBody
                            )
                        },
                        onClick = {
                            viewModel.selectedCategory = category
                            isCategoryDropDownExpanded.value = false
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