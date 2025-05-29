package info.imtushar.expensetracker.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import info.imtushar.expensetracker.ui.theme.regularBody
import kotlinx.coroutines.delay

@Composable
fun CustomToast(
    message: String,
    backgroundColor: Color,
    durationMillis: Long = 2000L, // Default 3 seconds
    onDismiss: () -> Unit = {}
) {
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(isVisible) {
        if (isVisible) {
            delay(durationMillis)
            isVisible = false
            onDismiss()
        }
    }

    if (isVisible) {
        Popup(
            alignment = Alignment.BottomCenter,
            onDismissRequest = { isVisible = false }
        ) {
            Box(
                modifier = Modifier
                    .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                    .background(backgroundColor, RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = message,
                    color = Color.White,
                    style = regularBody
                )
            }
        }
    }
}