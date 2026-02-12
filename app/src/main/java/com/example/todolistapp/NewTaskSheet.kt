package com.example.todolistapp

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todolistapp.RoomDatabase.TaskItem
import com.example.todolistapp.RoomDatabase.TaskViewModel
import com.example.todolistapp.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalTime

class NewTaskSheet(var taskItemTS: TaskItem?) : BottomSheetDialogFragment(){

    private lateinit var bindingFrg: FragmentNewTaskSheetBinding
    private lateinit var TVMfrg : TaskViewModel
    private var dueTime: LocalTime? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    { super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()
        if (taskItemTS != null)
        {
            bindingFrg.tvTitleFrgId.text = "Edit Task"
// لجعل الحقول قابل لتعديل
            var editable = Editable.Factory.getInstance()
            bindingFrg.etNameFragId.text = editable.newEditable(taskItemTS!!.nameTI)
            bindingFrg.etDescriptionFragId.text = editable.newEditable(taskItemTS!!.descTI)
//            ..
            if (taskItemTS!!. dueTime() != null){
                dueTime = taskItemTS!!. dueTime()!!
                updateTimeButtonText()
            }
        }
        else
        {
            bindingFrg.tvTitleFrgId.text = "New Task"
        }

        TVMfrg = ViewModelProvider(activity).get(TaskViewModel::class.java)
        bindingFrg.btnSaveFragId.setOnClickListener{
            saveActivity()
        }
//        ..
        bindingFrg.btnSelectTimeFragId.setOnClickListener {
            openTimePicker()
        }
    }
// ..
    private fun openTimePicker() {
        if (dueTime == null){
            dueTime = LocalTime.now()
            var listenerd = TimePickerDialog.OnTimeSetListener{ _, selectedHour, selectedMinute ->
                dueTime = LocalTime.of(selectedHour, selectedMinute)
                updateTimeButtonText()
            }
            var dialog = TimePickerDialog(activity, listenerd,dueTime!! .hour,dueTime!! .minute, true)
            dialog . setTitle("Task Due")
            dialog.show()
        }
    }
//..
    private fun updateTimeButtonText() {
        bindingFrg .btnSelectTimeFragId . text = String.format("%02d:%02d", dueTime!!.hour, dueTime!! . minute)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
// كلاس الانفلات
        bindingFrg = FragmentNewTaskSheetBinding.inflate(inflater, container, false)
        return bindingFrg.root
    }

    private fun saveActivity()
    {
        var nameTS = bindingFrg .etNameFragId        .text . toString()
        var descTS = bindingFrg .etDescriptionFragId .text . toString()

        var dueTimeString = if (dueTime == null) null else TaskItem.timeFormatter.format(dueTime)

        if (taskItemTS == null)
        {
            var newTaskTS = TaskItem(nameTS , descTS , dueTimeString, null)
            TVMfrg . addTaskItem(newTaskTS)
        }
        else
        {
            taskItemTS!! . nameTI    = nameTS
            taskItemTS!! . descTI    = descTS
            taskItemTS!! . dueTimeTI = dueTimeString
            TVMfrg . updateTaskItem ( taskItemTS!!)
        }
        bindingFrg . etNameFragId        .setText("")
        bindingFrg . etDescriptionFragId .setText("")
        dismiss()
    }

}
