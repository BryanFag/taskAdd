package com.devup.tarefa.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devup.tarefa.data.entity.TaskEntity
import com.devup.tarefa.data.entity.UserEntity
import com.devup.tarefa.data.repository.TaskRepository
import com.devup.tarefa.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.devup.tarefa.data.singleton.UserSingleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private val onUserId = UserSingleton.id
    val tasks: Flow<List<TaskEntity>> = taskRepository.getAllByUserId(onUserId)
    fun onUserSelected(user: UserEntity) = viewModelScope.launch {
        UserSingleton.id = user.id
        UserSingleton.name = user.name
    }
    fun insertTask(task: TaskEntity) = viewModelScope.launch {
        taskRepository.insert(task)
    }

}
