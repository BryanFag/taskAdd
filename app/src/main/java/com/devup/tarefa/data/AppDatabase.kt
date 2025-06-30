package com.devup.tarefa.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devup.tarefa.data.datasource.local.TaskDao
import com.devup.tarefa.data.datasource.local.UserDao
import com.devup.tarefa.data.entity.TaskEntity
import com.devup.tarefa.data.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        TaskEntity::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao
}
