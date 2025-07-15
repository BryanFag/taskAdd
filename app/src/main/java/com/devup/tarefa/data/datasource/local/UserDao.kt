package com.devup.tarefa.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.devup.tarefa.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user WHERE id = :userId")
    fun getUserId(userId: Int): UserEntity

    @Query("SELECT * FROM user WHERE email = :userEmail")
    suspend fun getUserEmail(userEmail: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)

    @Update
    suspend fun update(user: UserEntity)
}
