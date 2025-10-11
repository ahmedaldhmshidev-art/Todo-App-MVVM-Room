package com.example.todolistapp.RoomDatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import java.time.LocalDate
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class TaskViewModel(private val repository: TaskItemRepository):ViewModel() {

    var taslItemVM: LiveData<List<TaskItem>> = repository.allTaskItems.asLiveData()

    fun addTaskItem(newTaskItem: TaskItem) = viewModelScope.launch {
        repository.insertTaskItem(newTaskItem)
    }
    fun updateTaskItem(taskItem: TaskItem) = viewModelScope.launch {
        repository.updateTaskItem(taskItem)

    }
    fun deletedTaskItem(taskItem: TaskItem) = viewModelScope.launch {
        repository.deletedTaskItem(taskItem)
    }

    fun setCompleted(taskItem: TaskItem) = viewModelScope.launch {

        if (!taskItem.isCompleted())
            taskItem.completedDateTI = TaskItem.dateFormatter.format(LocalDate.now())
        repository.updateTaskItem(taskItem)

    }

    class TaskItemModelFactory(private val repository: TaskItemRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TaskViewModel::class.java))
                return TaskViewModel(repository) as T

            throw IllegalArgumentException("Unknown Class for View Model")
        }
    }
}