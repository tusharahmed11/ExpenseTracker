package info.imtushar.expensetracker.components.common

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import info.imtushar.expensetracker.ui.theme.PrimaryVariant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    datePickerState: DatePickerState,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {


    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                if (datePickerState.selectedDateMillis != null){
                    onDateSelected(datePickerState.selectedDateMillis)
                }
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState, colors = DatePickerDefaults.colors(
            yearContentColor = Color.White,
            titleContentColor = Color.White,
            subheadContentColor = Color.White,
            weekdayContentColor = Color.White,
            dayContentColor = Color.White,
            selectedDayContainerColor = PrimaryVariant,
            selectedDayContentColor = Color.White,
            todayContentColor = Color.White,
            headlineContentColor = Color.White,
            navigationContentColor = Color.White,
        ))
    }
}