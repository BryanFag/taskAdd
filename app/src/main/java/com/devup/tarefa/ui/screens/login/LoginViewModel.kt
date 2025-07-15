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
import com.devup.tarefa.data.singleton.UserSingleton

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun getUserEmail(email: String, password: String, onResult: (Boolean) -> Unit) = viewModelScope.launch {
        val userinfo = userRepository.getUserEmail(email)
        if (userinfo != null && userinfo.password == password) {
            onResult(true)
            setUserActive(userinfo)
        } else {
            onResult(false)
        }
    }

    fun setUserActive(userId: UserEntity) {
        UserSingleton.id = userId.id
        UserSingleton.name = userId.name
        UserSingleton.picture = userId.picture
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
