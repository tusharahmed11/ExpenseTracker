package info.imtushar.expensetracker.data

import java.time.format.DateTimeFormatter

object AppConstants {

    val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")
}