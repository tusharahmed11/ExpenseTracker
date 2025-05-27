package info.imtushar.expensetracker.screens.expenses

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import info.imtushar.expensetracker.R
import info.imtushar.expensetracker.components.common.SimpleToolbar
import info.imtushar.expensetracker.ui.theme.BackgroundElevated
import info.imtushar.expensetracker.ui.theme.BorderColor
import info.imtushar.expensetracker.ui.theme.LabelSecondary
import info.imtushar.expensetracker.ui.theme.SystemGray04
import info.imtushar.expensetracker.ui.theme.largeTitle
import info.imtushar.expensetracker.ui.theme.regularBody
import info.imtushar.expensetracker.ui.theme.regularHeadline
import info.imtushar.expensetracker.ui.theme.regularTitle2

@Composable
fun ExpensesScreen(modifier: Modifier = Modifier) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {
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
                                "this week",
                                color = Color.White,
                                style = regularTitle2,
                                modifier = Modifier.padding(end = 10.dp)
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.up_down_icon),
                                contentDescription = "up down icon",
                                tint = LabelSecondary
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "$",
                        color = LabelSecondary,
                        style = regularTitle2,
                        modifier = Modifier.padding(end = 4.dp)
                    )

                    Text(
                        text = "1250.98",
                        color = Color.White,
                        style = largeTitle
                    )
                }
            }
        }

        Text(
            text = "Today",
            color = LabelSecondary,
            style = regularHeadline,
            modifier = Modifier.padding(start = 16.dp, bottom = 10.dp)
        )

        HorizontalDivider(thickness = 2.dp, color = BorderColor, modifier = Modifier.padding(horizontal = 16.dp))



    }
}

@Preview(showBackground = true)
@Composable
private fun ExpensesScreenPrev() {
    ExpensesScreen()
}