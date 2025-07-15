package com.devup.tarefa.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.devup.tarefa.data.entity.UserEntity
import com.devup.tarefa.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.devup.tarefa.data.repository.TaskRepository

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val taskRepository: TaskRepository
) : ViewModel() {

    val users = userRepository.getAll().asLiveData()

    fun getUserEmail(email: String, password: String, onResult: (Boolean) -> Unit) = viewModelScope.launch {
        val userinfo = userRepository.getUserEmail(email)
        if (userinfo != null && userinfo.password == password) {
            onResult(true)
        } else {
            onResult(false)
        }
    }

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
