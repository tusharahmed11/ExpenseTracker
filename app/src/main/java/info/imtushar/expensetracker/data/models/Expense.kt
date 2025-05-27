package info.imtushar.expensetracker.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import info.imtushar.expensetracker.utils.Converters
import info.imtushar.expensetracker.utils.Recurrence
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

@Entity(tableName = DatabaseConstants.TBL_EXPENSE,
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE // Deletes expenses if category is deleted
        )
    ])

@TypeConverters(Converters::class)
data class Expense(
    @PrimaryKey var id : UUID = UUID.randomUUID(),
    val title: String,
    val amount: Double,
    val categoryId: UUID,
    val date: LocalDate,
    val time: LocalTime,
    val recurrence: Recurrence,
    val note: String? = null
)