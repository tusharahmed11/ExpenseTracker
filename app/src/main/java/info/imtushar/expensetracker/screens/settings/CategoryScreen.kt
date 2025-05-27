package info.imtushar.expensetracker.screens.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.delete
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import info.imtushar.expensetracker.R
import info.imtushar.expensetracker.components.common.SimpleToolbar
import info.imtushar.expensetracker.ui.theme.BackgroundElevated
import info.imtushar.expensetracker.ui.theme.LabelSecondary
import info.imtushar.expensetracker.ui.theme.Primary
import info.imtushar.expensetracker.ui.theme.regularBody
import info.imtushar.expensetracker.viewmodel.CategoryViewModel


@Composable
fun CategoryScreen(
    categoryViewModel: CategoryViewModel = hiltViewModel(),
    onBackAction: () -> Unit = {}
) {


    Column(modifier = Modifier.fillMaxSize()) {
        SimpleToolbar(title = "Categories", onBackAction = onBackAction)

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp)
            .background(color = BackgroundElevated, shape = RoundedCornerShape(size = 6.dp))) {

            // TODO: show category list 
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp), verticalAlignment = Alignment.CenterVertically) {

            Box(modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(size = 14.dp)
                )
                .width(24.dp)
                .height(24.dp)
                .background(color = Color(0xFFBF5AF2), shape = RoundedCornerShape(size = 14.dp))
                .padding(start = 20.dp, top = 13.dp, end = 20.dp, bottom = 13.dp))

            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .background(color = BackgroundElevated, shape = RoundedCornerShape(4.dp))
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BasicTextField(
                        state = categoryViewModel.categoryTextFieldState,
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp),
                        textStyle = regularBody.copy(color = LabelSecondary),
                    )
                }
                AnimatedVisibility(visible = categoryViewModel.categoryTextFieldState.text.isNotBlank()) {
                    Icon(
                        painter = painterResource(R.drawable.xmark_circle),
                        contentDescription = "Delete icon",
                        modifier = Modifier.clickable {
                            categoryViewModel.categoryTextFieldState.edit { delete(0, length) }
                        }
                    )
                }
            }

            IconButton(
                onClick = {

                },
                modifier = Modifier
                    .width(60.dp)
                    .height(48.dp)
                    .background(color = Primary, shape = RoundedCornerShape(size = 10.dp))
            ) {
                Icon(painter = painterResource(R.drawable.arrow_right_circle), contentDescription = "Add icon",
                    tint = Color.White, modifier = Modifier.padding(vertical = 14.dp, horizontal = 20.dp))
            }

        }



    }
}

@Preview
@Composable
private fun CategoryScreenPrev() {
    CategoryScreen()
}