package info.imtushar.expensetracker.data.dao

import androidx.room.Dao
import androidx.room.Query
import info.imtushar.expensetracker.data.models.DatabaseConstants.TBL_EXPENSE
import info.imtushar.expensetracker.data.models.Expense
import info.imtushar.expensetracker.data.dao.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao: BaseDao<Expense> {

    @Query("SELECT * FROM $TBL_EXPENSE")
    fun getAllExpenses(): Flow<List<Expense>>

}