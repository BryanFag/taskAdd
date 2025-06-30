package com.devup.tarefa.data.repository

import com.devup.tarefa.data.datasource.local.UserDao
import com.devup.tarefa.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    fun getAll(): Flow<List<UserEntity>> {
        return  userDao.getAll()
    }

    fun getUserId(userId: Int): UserEntity {
        return  userDao.getUserId(userId)
    }

    suspend fun insert(user: UserEntity) {
       return userDao.insert(user)
    }
    suspend fun delete(user: UserEntity) {
        return userDao.delete(user)
    }
    suspend fun update(user: UserEntity) {
         return userDao.update(user)
    }
}
