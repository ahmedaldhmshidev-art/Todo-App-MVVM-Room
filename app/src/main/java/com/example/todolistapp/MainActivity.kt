package com.example.todolistapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp.RoomDatabase.TaskItem
import com.example.todolistapp.RoomDatabase.TaskViewModel
import com.example.todolistapp.RoomDatabase.ToDoTaskItemApp
import com.example.todolistapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , TaskItemClickListener {

    private lateinit var binding: ActivityMainBinding

    private val viewModelTaskItem : TaskViewModel by viewModels {
        TaskViewModel.TaskItemModelFactory((application as ToDoTaskItemApp).repository)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



//        عرض الفراق منت
        binding.btnNewTaskId.setOnClickListener{
            NewTaskSheet(null) . show(supportFragmentManager, "newTaskTag")

        }

        setRecyclerView()


    }

    private fun setRecyclerView() {
        val mainActivity = this
        viewModelTaskItem . taslItemVM . observe(this){
            binding . todoListRecyclerViewActMainId . apply {
                layoutManager = LinearLayoutManager (applicationContext)
                adapter = TaskItemAdapter(it , mainActivity)
            }
        }
    }
//  دوال تم اعادة استخدامها عند توريث من الانترفيس
    override fun editTaskItemCL(taskItemCL: TaskItem) {
        NewTaskSheet(taskItemCL).show(supportFragmentManager,"newTaskTag" )
    }

    override fun completeTaskItemCL(taskItemCL: TaskItem) {
        viewModelTaskItem.setCompleted(taskItemCL)


    }

    override fun deletedTaskItemCL(taskItemCL: TaskItem) {
        viewModelTaskItem.deletedTaskItem(taskItemCL)
    }

}

