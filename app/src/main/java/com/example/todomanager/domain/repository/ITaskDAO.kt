package com.example.todomanager.domain.repository

import com.example.todomanager.domain.model.Task

interface ITaskDAO {
    fun registerTask(task: Task)
    fun updateTask(task: Task)
    fun deleteTask(id: String)
    fun getAllTask(): List<Task>
}
