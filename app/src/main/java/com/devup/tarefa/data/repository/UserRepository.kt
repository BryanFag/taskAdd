package com.devup.tarefa.data.repository

import com.devup.tarefa.data.datasource.local.UserDao
import com.devup.tarefa.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    fun getAll(): Flow<List<UserEntity>> {
        return userDao.getAll()
    }

    suspend fun insert(user: UserEntity) = userDao.insert(user)

    suspend fun delete(user: UserEntity) = userDao.delete(user)

    suspend fun update(user: UserEntity) = userDao.update(user)
}