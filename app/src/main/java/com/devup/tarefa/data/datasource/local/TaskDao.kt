package com.devup.tarefa.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.devup.tarefa.data.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAll(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task WHERE userId = :userId")
    fun getAllByUserId(userId: Int): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userId: TaskEntity)

    @Delete
    suspend fun delete(userId: TaskEntity)

    @Update
    suspend fun update(userId: TaskEntity)
}
