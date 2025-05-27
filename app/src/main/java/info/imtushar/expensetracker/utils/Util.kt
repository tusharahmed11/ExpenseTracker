package info.imtushar.expensetracker.utils

import android.graphics.Color
import androidx.core.graphics.toColorInt

fun String.toColorInt(): Int{
    // Remove the "#" if present and parse the hex string to an integer
    return this.toColorInt()
}