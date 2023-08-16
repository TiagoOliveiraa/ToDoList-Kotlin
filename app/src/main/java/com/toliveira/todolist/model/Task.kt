package com.toliveira.todolist.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


//Here we create a table on the room database
//We need to specify entity and inside it the table name
@Parcelize
@Entity(tableName = "task_table")
class Task(
    //We need to indicate that this "id" value is the primary key for the table
    //The autoGenerate = true, tells the program to generate the id automatically
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val resolved: Boolean
        ) : Parcelable