package com.devup.tarefa.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devup.tarefa.data.entity.TaskEntity
import com.devup.tarefa.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.devup.tarefa.data.singleton.UserSingleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
) : ViewModel() {

    private val onUserId = UserSingleton.id
    val tasks: Flow<List<TaskEntity>> = taskRepository.getAllByUserId(onUserId)

    fun insertTask(task: TaskEntity) = viewModelScope.launch {
        taskRepository.insert(task)
    }

    fun updateTask(tasks: List<TaskEntity>) = viewModelScope.launch {
        taskRepository.update(tasks)
    }

    fun deleteTask(task: TaskEntity) = viewModelScope.launch {
        taskRepository.delete(task)
    }

}
