package info.imtushar.expensetracker.repository

import info.imtushar.expensetracker.data.dao.ExpenseDao
import info.imtushar.expensetracker.data.models.Expense
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExpenseRepository @Inject constructor(private val expenseDao: ExpenseDao)  {

    suspend fun addExpense(expense: Expense) {
        expenseDao.insert(expense)
    }

    fun getAllExpenses(): Flow<List<Expense>> {
        return expenseDao.getAllExpenses()
    }

}