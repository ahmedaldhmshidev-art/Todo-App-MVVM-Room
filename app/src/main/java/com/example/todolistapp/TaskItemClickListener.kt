package com.example.todolistapp

import com.example.todolistapp.RoomDatabase.TaskItem

interface TaskItemClickListener
{
    fun editTaskItemCL(taskItemCL: TaskItem)
    fun completeTaskItemCL(taskItemCL: TaskItem)
    fun deletedTaskItemCL(taskItemCL: TaskItem)
}