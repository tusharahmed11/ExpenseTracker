package info.imtushar.expensetracker.utils


import android.graphics.Color.red
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt

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