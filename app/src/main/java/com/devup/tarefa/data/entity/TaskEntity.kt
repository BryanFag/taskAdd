package com.devup.tarefa.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val userId: Int = 0,
    val name: String = "",
    val description: String = "",
    val insertDate: Long = System.currentTimeMillis(),
    val dateFinish : String = "",
    val timeFinish : String = "",
    val status: Int = 0, // 0: PENDING, 1: IN_PROGRESS, 2: COMPLETED, 3: CANCELLED
)