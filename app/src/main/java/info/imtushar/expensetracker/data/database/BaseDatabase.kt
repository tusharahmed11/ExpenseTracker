package info.imtushar.expensetracker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import info.imtushar.expensetracker.data.dao.CategoryDao
import info.imtushar.expensetracker.data.dao.ExpenseDao
import info.imtushar.expensetracker.data.models.Category
import info.imtushar.expensetracker.data.models.DatabaseConstants
import info.imtushar.expensetracker.data.models.Expense
import info.imtushar.expensetracker.utils.Converters

@Database(
    entities = [
        Expense::class,
        Category::class
    ],
    version = DatabaseConstants.DATABASE_VERSION,exportSchema = false
)
@TypeConverters(Converters::class)
abstract class BaseDatabase : RoomDatabase() {

    abstract val categoryDao: CategoryDao
    abstract val expenseDao: ExpenseDao

    companion object {
        private var INSTANCE: BaseDatabase? = null

        fun getDatabase(ctx: Context): BaseDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        ctx, BaseDatabase::class.java, DatabaseConstants.DATABASE_NAME
                    ).build()
                }
            }

            return INSTANCE!!
        }
    }
}