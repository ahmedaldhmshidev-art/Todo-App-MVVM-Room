package com.example.todolistapp

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.RoomDatabase.TaskItem
import com.example.todolistapp.databinding.TaskItemCellBinding
import java.time.format.DateTimeFormatter


class TaskItemViewHolder (
    private var contextVH : Context ,
    private var bindingVH : TaskItemCellBinding,
    private var clickListenerVH: TaskItemClickListener
): RecyclerView.ViewHolder(bindingVH . root)
{



    var timeFormat = DateTimeFormatter.ofPattern("HH:mm")

    fun bindTaskItemVH (taskItem : TaskItem){
        bindingVH . tvNamePaceHolderCellId . text = taskItem . nameTI
        bindingVH . tvDescCellId . text           = taskItem . descTI

        if (taskItem.isCompleted()) {
            bindingVH . tvNamePaceHolderCellId . paintFlags = Paint . STRIKE_THRU_TEXT_FLAG
            bindingVH . tvDueTimeCellId        . paintFlags = Paint .  STRIKE_THRU_TEXT_FLAG
        }
        bindingVH . imgBtnCompleteButtonCellId . setImageResource(taskItem . imageResource())
        bindingVH . imgBtnCompleteButtonCellId . setColorFilter(  taskItem . imageColor(contextVH))

        bindingVH . imgBtnCompleteButtonCellId . setOnClickListener {
            clickListenerVH . completeTaskItemCL(taskItem)
        }
        bindingVH . taskCellContainerId . setOnClickListener{
            clickListenerVH . editTaskItemCL(taskItem)
        }
        bindingVH . taskCellContainerId .setOnLongClickListener {
            clickListenerVH . deletedTaskItemCL(taskItem)
            true
        }
        if ( taskItem . dueTime() != null )
            bindingVH . tvDueTimeCellId . text = timeFormat . format(taskItem.dueTime())
        else
            bindingVH . tvDueTimeCellId . text = ""
    }
}