package com.kdnt.notecute.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.text.DateFormat

@Parcelize
@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "task_name") var name : String?,
    @ColumnInfo(name = "completed_state") val isCompleted : Boolean = false,
    @ColumnInfo(name = "time_create") val timeCreated : Long = System.currentTimeMillis()
) : Parcelable {
    fun formatTimeTask() : String{
        return DateFormat.getDateInstance().format(timeCreated)
    }
}