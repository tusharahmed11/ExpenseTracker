package info.imtushar.expensetracker.repository

import info.imtushar.expensetracker.data.dao.CategoryDao
import info.imtushar.expensetracker.data.models.Category
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryDao: CategoryDao) {

    suspend fun insert(category: Category) {
        categoryDao.insert(category)
    }

    fun getAllCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategories()
    }

    suspend fun delete(categoryId: UUID) {
        categoryDao.delete(categoryId)
    }
}