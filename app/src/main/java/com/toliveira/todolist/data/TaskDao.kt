package com.toliveira.todolist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.DeleteTable
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.toliveira.todolist.model.Task


//This is a DAO (Data Access Object), we create this to interact with data from a table
//In the Dao interface we can create, read, update and delete information (CRUD)

//First thing to do is to inform the interface that this is a dao object with @Dao above the interface
@Dao
interface TaskDao {

    //Since we are trying to add a new task into the table, we use the insert to inform that this function add a task
    // We use the suspend keyword, in order to use kotlin coroutines
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM task_table")
    suspend fun deleteAll()

    //This function will read all the data from the table.
    //We use the query @Query to create an sql query for the table.
    @Query("SELECT * FROM task_table ORDER BY id ASC " )
    fun readAllData(): LiveData<List<Task>>

}