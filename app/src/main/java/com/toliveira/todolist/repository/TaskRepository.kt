package com.toliveira.todolist.repository

import androidx.lifecycle.LiveData
import com.toliveira.todolist.data.TaskDao
import com.toliveira.todolist.model.Task

data class TaskRepository(private val taskDao: TaskDao){

    val readAllData: LiveData<List<Task>> = taskDao.readAllData()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }

    suspend fun deleteAll(){
        taskDao.deleteAll()
    }
}
