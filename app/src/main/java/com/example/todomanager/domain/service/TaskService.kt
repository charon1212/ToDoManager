package com.example.todomanager.domain.service

import com.example.todomanager.domain.model.Task
import com.example.todomanager.domain.repository.ITaskDAO
import java.util.*

class TaskService {
    private val dao: ITaskDAO

    constructor(dao: ITaskDAO) {
        this.dao = dao
    }

    fun getTasks(): List<Task> {
        return dao.getAllTask()
    }

    /**
     * タスクを作成してDBに登録し、タスクを返却する。
     * IDは自動裁判する。
     */
    fun createTask(title: String, detail: String): Task {
        val uuid = UUID.randomUUID().toString()
        val task = Task(id = uuid, title = title, detail = detail)
        dao.registerTask(task)
        return task
    }

    fun updateTask(task: Task){
        dao.updateTask(task)
    }

    fun deleteTask(task: Task){
        dao.deleteTask(task.id)
    }

}