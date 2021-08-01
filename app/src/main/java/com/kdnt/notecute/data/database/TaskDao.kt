package com.kdnt.notecute.data.database

import androidx.room.*
import com.kdnt.notecute.data.entity.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(tasks: Task)

    @Update
    fun updateTask(tasks: Task)

    @Delete
    fun deleteTask(tasks: Task)

    @Query("Delete from task")
    fun deleteAllTask()

    @Query("SELECT * FROM task")
    fun getAllTask(): MutableList<Task>

    @Query("select * from task where completed_state =:isComplete")
    fun getAllTaskDone(isComplete : Boolean) : MutableList<Task>

    @Query("select * from task where task_name =:name")
    fun getTaskByName(name: String): Task

}