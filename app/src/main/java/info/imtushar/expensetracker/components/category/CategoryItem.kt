package info.imtushar.expensetracker.components.category

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import info.imtushar.expensetracker.R
import info.imtushar.expensetracker.components.common.CustomIconButton
import info.imtushar.expensetracker.data.models.Category
import info.imtushar.expensetracker.ui.theme.BackgroundElevated
import info.imtushar.expensetracker.ui.theme.BorderColor
import info.imtushar.expensetracker.ui.theme.Destructed
import info.imtushar.expensetracker.ui.theme.IconColor
import info.imtushar.expensetracker.ui.theme.regularBody
import info.imtushar.expensetracker.utils.toColorInt
import kotlin.math.abs

@Composable
fun CategoryItem(
    category: Category,
    isLastItem: Boolean = false,
) {



    Column(
        modifier = Modifier.fillMaxWidth().background(color = BackgroundElevated)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 11.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = category.name,
                color = Color.White,
                style = regularBody
            )

            Box(modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(size = 14.dp)
                )
                .width(20.dp)
                .height(20.dp)
                .background(color = Color(category.color.toColorInt()), shape = RoundedCornerShape(size = 14.dp))
                .padding(start = 20.dp, top = 13.dp, end = 20.dp, bottom = 13.dp))
        }
        if (!isLastItem){
            HorizontalDivider(thickness = .5.dp, color = BorderColor)
        }

    }

}


@Composable
fun SwipeableCategoryItem(
    category: Category,
    isLastItem: Boolean,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    val deleteButtonWidth = 60.dp
    val dragThreshold = with(LocalDensity.current) { deleteButtonWidth.toPx() }
    val animatedOffsetX by animateFloatAsState(targetValue = offsetX, label = "Swipe Offset")

    Box(
        modifier = modifier
            .fillMaxWidth()
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta ->
                    val newOffset = (offsetX + delta).coerceIn(-dragThreshold, 0f)
                    offsetX = newOffset
                },
                onDragStopped = {
                    offsetX = if (abs(offsetX) < dragThreshold / 2) {
                        0f // Snap back if swipe is less than half
                    } else {
                        -dragThreshold // Fully reveal delete button
                    }
                }
            )
    ) {
        // Delete Button (Background)
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .width(deleteButtonWidth)
                .fillMaxHeight()
                .padding(start = 10.dp)
                .background(Destructed)
        ) {
            Icon(
                painter = painterResource(R.drawable.trash_can),
                contentDescription = "Delete category",
                modifier = Modifier
                    .align(Alignment.Center)
                    .clickable {
                        onDelete()
                        offsetX = 0f // Reset offset after deletion
                    }
                    .padding(4.dp),
                tint = Color.White
            )
        }

        // Category Item (Foreground)
        Box(
            modifier = Modifier
                .offset { IntOffset(animatedOffsetX.toInt(), 0) }
                .fillMaxWidth()
                .background(Color.Transparent)
        ) {
            CategoryItem(
                category = category,
                isLastItem = isLastItem
            )
        }
    }
}

@Preview
@Composable
private fun CategoryItemPrev() {
    CategoryItem(category = Category(
        name = "Food",
        color = "#FFFFFF"
    ))
}