package com.devup.tarefa.ui.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.devup.tarefa.data.entity.UserEntity
import com.devup.tarefa.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.LiveData

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    val users: LiveData<List<UserEntity>> = repository.getAll().asLiveData()

    fun insert(user: UserEntity) = viewModelScope.launch {
        repository.insert(user)
    }

    fun delete(user: UserEntity) = viewModelScope.launch {
        repository.delete(user)
    }

    fun update(user: UserEntity) = viewModelScope.launch {
        repository.update(user)
    }
}
