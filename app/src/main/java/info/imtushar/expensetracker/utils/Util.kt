package info.imtushar.expensetracker.utils


import android.graphics.Color.red
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import java.time.LocalDate

fun String.toColorInt(): Int{
    // Remove the "#" if present and parse the hex string to an integer
    return this.toColorInt()
}

fun Color.toHexRgb(): String {
    val red = (red * 255).toInt()
    val green = (green * 255).toInt()
    val blue = (blue * 255).toInt()
    return String.format("#%02X%02X%02X", red, green, blue)
}

fun String.toTitleCase(): String {
    return if (isNotEmpty()) {
        substring(0, 1).uppercase() + substring(1).lowercase()
    } else {
        this
    }
}

fun String.toReadableFilter(): String {
    return split("_")
        .joinToString(" ") { word ->
            word.lowercase().replaceFirstChar { it.uppercase() }
        }
}

// PastOrPresentSelectableDates.kt
@OptIn(ExperimentalMaterial3Api::class)
object PastOrPresentSelectableDates: SelectableDates {
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        return utcTimeMillis <= System.currentTimeMillis()
    }

    override fun isSelectableYear(year: Int): Boolean {
        return year <= LocalDate.now().year
    }
}