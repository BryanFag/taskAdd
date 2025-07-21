package com.devup.tarefa.data.repository

import com.devup.tarefa.data.datasource.local.TaskDao
import com.devup.tarefa.data.entity.TaskEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {
    fun getAll(): Flow<List<TaskEntity>> {
        return  taskDao.getAll()
    }
    fun getAllByUserId(idUser: Int): Flow<List<TaskEntity>> {
        return taskDao.getAllByUserId(idUser)
    }
    suspend fun insert(idUser: TaskEntity) {
        return taskDao.insert(idUser)
    }
    suspend fun delete(idUser: TaskEntity) {
        return taskDao.delete(idUser)}

    suspend fun update(tasks: List<TaskEntity>) {
        return taskDao.update(tasks)
    }
}
