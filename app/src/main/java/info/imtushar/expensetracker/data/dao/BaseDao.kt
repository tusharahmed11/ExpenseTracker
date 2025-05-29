package info.imtushar.expensetracker.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(obj : T)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertList(obj: List<T>)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(vararg obj : T)

    @Update
    suspend fun update(obj: T)

    @Delete
    suspend fun delete(obj: T)

}