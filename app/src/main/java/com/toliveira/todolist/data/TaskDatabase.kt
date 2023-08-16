package com.toliveira.todolist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.toliveira.todolist.model.Task


// Here we create the Room database
// We need to extend the database from RoomDatabase() and we need to put the annotation @Database
// Inside the annotation we need to specify all the entities (tables) inside the database
@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase: RoomDatabase(){

    abstract fun taskDao() : TaskDao


    // Here we will start the database
    // If there is already a database running (INSTANCE != null) then we just return the instance of database already working
    companion object{
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}