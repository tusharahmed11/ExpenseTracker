package info.imtushar.expensetracker.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import info.imtushar.expensetracker.data.dao.CategoryDao
import info.imtushar.expensetracker.data.dao.ExpenseDao
import info.imtushar.expensetracker.data.database.BaseDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BaseDatabase {
        return BaseDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideCategoryDao(database: BaseDatabase): CategoryDao {
        return database.categoryDao
    }

    @Provides
    @Singleton
    fun provideExpenseDao(database: BaseDatabase): ExpenseDao {
        return database.expenseDao
    }
}