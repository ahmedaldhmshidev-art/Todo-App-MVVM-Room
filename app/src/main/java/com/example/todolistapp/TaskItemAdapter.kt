package com.example.todolistapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.RoomDatabase.TaskItem
import com.example.todolistapp.databinding.TaskItemCellBinding

class TaskItemAdapter (
    private val TIAdapter : List<TaskItem>,
    private var clickListenerA: TaskItemClickListener
) : RecyclerView.Adapter<TaskItemViewHolder>()
{
//    هذه الداله تدعئ مره واحدة فقط لكل عنصر
//    وضيفتها عمل انفلات لتصميم المراد عرضة في Recycler
//    ومن ثم تحمل هذه الصفحة الي كلاس holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
//عمل الانفلات لصفحة التصميم
        val fromTIA = LayoutInflater.from(parent.context)
        val bindingTIA = TaskItemCellBinding . inflate(fromTIA , parent , false)

        val downloadDesign =  TaskItemViewHolder(parent . context , bindingTIA , clickListenerA)
        return downloadDesign
    }
    // دالة تستدعئ عند الربط
// تربط ما بين البيانات الي في كلاس taskItem و التصميم
// تيستقبل التصميم  من كلاس holder ثم تعبيها بالبيانات الموجودة في  كلاس TaskItem
    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        holder . bindTaskItemVH(TIAdapter[position])
    }
//ترجع طول القائمة
    override fun getItemCount(): Int = TIAdapter.size


}