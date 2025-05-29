package info.imtushar.expensetracker.data.dao

import androidx.room.Dao
import androidx.room.Query
import info.imtushar.expensetracker.data.models.Category
import info.imtushar.expensetracker.data.models.DatabaseConstants.TBL_CATEGORY
import info.imtushar.expensetracker.data.dao.BaseDao
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface CategoryDao: BaseDao<Category> {

    @Query("SELECT * FROM $TBL_CATEGORY")
    fun getAllCategories(): Flow<List<Category>>

    @Query("DELETE FROM $TBL_CATEGORY WHERE id = :categoryId")
    suspend fun delete(categoryId: UUID)
}