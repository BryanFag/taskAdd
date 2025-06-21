package com.devup.tarefa.ui.screens.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.devup.tarefa.data.AppDatabase
import com.devup.tarefa.data.entity.UserEntity
import com.devup.tarefa.data.repository.UserRepository
import kotlinx.coroutines.launch

open class UserViewModel(application: Application) : AndroidViewModel(application) {

    open val users: LiveData<List<UserEntity>>
        get() = this.repository.getAll().asLiveData()

    private val repository: UserRepository

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun getAll() = viewModelScope.launch {
        repository.getAll()
    }
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

