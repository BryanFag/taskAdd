package com.devup.tarefa.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.devup.tarefa.data.entity.UserEntity
import com.devup.tarefa.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devup.tarefa.data.repository.TaskRepository
import com.devup.tarefa.data.singleton.UserSingleton

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val taskRepository: TaskRepository
) : ViewModel() {

    val users = userRepository.getAll().asLiveData()

    fun insert(user: UserEntity) = viewModelScope.launch {
        userRepository.insert(user)
    }
    fun delete(user: UserEntity) = viewModelScope.launch {
        userRepository.delete(user)
    }
    fun update(user: UserEntity) = viewModelScope.launch {
        userRepository.update(user)
    }
}
