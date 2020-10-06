package com.example.todomanager.infrastructure.repository

import com.example.todomanager.domain.model.Task
import com.example.todomanager.domain.repository.ITaskDAO
import com.example.todomanager.infrastructure.entity.TaskEntity
import io.realm.Realm
import io.realm.kotlin.where

class TaskDAO : ITaskDAO {

    override fun registerTask(task: Task) {

        val realm = Realm.getDefaultInstance()
        realm.use { realm ->
            val taskEntity = TaskEntity(
                id = task.id,
                title = task.title,
                detail = task.detail
            )
            realm.beginTransaction()
            realm.insert(taskEntity)
            realm.commitTransaction()
        }

    }

    override fun updateTask(task: Task) {

        Realm.getDefaultInstance().use { realm ->
            val taskEntity = realm.where<TaskEntity>()
                .equalTo("id", task.id)
                .findFirst()
            realm.beginTransaction()
            taskEntity?.apply {
                this.title = task.title
                this.detail = task.detail
            }
            realm.commitTransaction()
        }

    }

    override fun deleteTask(id: String) {

        Realm.getDefaultInstance().use { realm ->
            val taskList = realm.where<TaskEntity>().equalTo("id", id).findAll()
            realm.beginTransaction()
            taskList.deleteAllFromRealm()
            realm.commitTransaction()
        }

    }

    override fun getAllTask(): List<Task> {

        Realm.getDefaultInstance().use { realm ->
            val result = realm.where<TaskEntity>().findAll()
            return result.map {
                Task(
                    id = it.id,
                    title = it.title,
                    detail = it.detail
                )
            }
        }

    }
}