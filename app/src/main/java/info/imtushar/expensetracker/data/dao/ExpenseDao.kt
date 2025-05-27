package info.imtushar.expensetracker.data.dao

import androidx.room.Dao
import info.imtushar.expensetracker.data.models.Expense
import info.imtushar.expensetracker.utils.BaseDao

@Dao
interface ExpenseDao: BaseDao<Expense> {
}