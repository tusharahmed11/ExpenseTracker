package info.imtushar.expensetracker.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import info.imtushar.expensetracker.components.common.CustomIconButton
import info.imtushar.expensetracker.components.common.SimpleToolbar
import info.imtushar.expensetracker.ui.theme.BackgroundElevated
import info.imtushar.expensetracker.ui.theme.BorderColor
import info.imtushar.expensetracker.ui.theme.Destructed
import info.imtushar.expensetracker.ui.theme.IconColor
import info.imtushar.expensetracker.ui.theme.regularBody

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onCategoryAction: () -> Unit = {}
) {

    Column(modifier = Modifier.fillMaxSize()) {
        SimpleToolbar(title = "Settings")

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .background(color = BackgroundElevated, shape = RoundedCornerShape(size = 6.dp))
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 11.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Categories",
                        color = Color.White,
                        style = regularBody
                    )
                    CustomIconButton(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                        contentDescription = "arrow right",
                        tint = IconColor,
                        onClick = onCategoryAction
                    )
                }

                HorizontalDivider(thickness = .5.dp, color = BorderColor)

                Text(
                    text = "Erase Data",
                    color = Destructed,
                    style = regularBody,
                    modifier = Modifier.padding(vertical = 11.dp)
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPrev() {
    SettingsScreen()
}