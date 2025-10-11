package com.example.todolistapp.RoomDatabase

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todolistapp.R
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "Task_Item_DB")
class TaskItem (
 @ColumnInfo(name = "name_DB")           var nameTI :String,
 @ColumnInfo(name = "desc_DB")           var descTI :String,
 @ColumnInfo(name = "dueTime_DB")        var dueTimeTI :String?,
 @ColumnInfo(name = "completedDate_DB")  var completedDateTI: String?,
 @PrimaryKey(autoGenerate = true)        var id:Int = 0
)




{
    fun completedDate(): LocalDate? = if (completedDateTI == null) null
    else LocalDate.parse(completedDateTI , dateFormatter)

    fun dueTime(): LocalTime? = if (dueTimeTI == null) null
    else LocalTime.parse(dueTimeTI , timeFormatter)


    fun isCompleted() = completedDate() != null
    fun imageResource(): Int = if (isCompleted()) R.drawable.checked_24 else R.drawable.unchecked_24
    fun imageColor(context: Context): Int =
        if (isCompleted()) purple(context) else black(context)

    private fun purple(context: Context) = ContextCompat.getColor(context, R.color.purple_500)
    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)


    companion object {
        val timeFormatter : DateTimeFormatter = DateTimeFormatter.ISO_TIME
        val dateFormatter : DateTimeFormatter = DateTimeFormatter.ISO_DATE
    }
}

