package com.example.todolistapp.RoomDatabase

import android.app.Application

class ToDoTaskItemApp : Application()
{
    private val database by lazy { TaskItemDatabase.getDatabase(this) }

    val repository by lazy { TaskItemRepository(database.taskItemDao()) }
}