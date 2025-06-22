package com.devup.tarefa.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devup.tarefa.data.datasource.local.UserDao
import com.devup.tarefa.data.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
