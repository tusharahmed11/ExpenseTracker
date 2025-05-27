package info.imtushar.expensetracker.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = DatabaseConstants.TBL_CATEGORY)
data class Category(
    @PrimaryKey var id : UUID = UUID.randomUUID(),
    val name: String,
    val color: String
)