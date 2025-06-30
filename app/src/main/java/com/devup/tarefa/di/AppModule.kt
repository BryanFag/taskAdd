package com.devup.tarefa.di

import android.content.Context
import androidx.room.Room
import com.devup.tarefa.data.AppDatabase
import com.devup.tarefa.data.datasource.local.TaskDao
import com.devup.tarefa.data.datasource.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context

    ): AppDatabase =
        Room.databaseBuilder(appContext, AppDatabase::class.java, "app_db").fallbackToDestructiveMigration().build()

    @Provides
    fun provideTaskDao(db: AppDatabase): TaskDao {
        return db.taskDao()
    }
    @Provides
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }
}