package com.kdnt.notecute.ui.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kdnt.notecute.core.base.BaseViewModel
import com.kdnt.notecute.data.database.TaskDatabase
import com.kdnt.notecute.data.entity.Task
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {
    private val mListTaskData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    private val mStatusTaskDoneData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    private var mListTask = mutableListOf<Task>()

    fun addTask(task: Task, context: Context) {
        viewModelScope.launch {
            TaskDatabase.getInstance(context).taskDao().insertTask(task)
            mListTask = TaskDatabase.getInstance(context).taskDao().getAllTask()
            mListTaskData.postValue(mListTask)
        }
    }

    fun onTaskCheckedChange(context: Context, task: Task, isChecked : Boolean){
        viewModelScope.launch {
            TaskDatabase.getInstance(context).taskDao().updateTask(task.copy(isCompleted = isChecked))
            mListTask = TaskDatabase.getInstance(context).taskDao().getAllTask()
            mListTaskData.postValue(mListTask)
        }
    }

    fun editTask(context: Context, task: Task){
        viewModelScope.launch {
            TaskDatabase.getInstance(context).taskDao().updateTask(task.copy(name = task.name))
        }
    }

    fun getAllTaskDone(context: Context) : MutableList<Task>{
        viewModelScope.launch {
            mListTask = TaskDatabase.getInstance(context).taskDao().getAllTaskDone(isComplete = true)
        }
        return mListTask
    }

    fun deleteAllTask(context: Context){
        viewModelScope.launch {
            TaskDatabase.getInstance(context).taskDao().deleteAllTask()
        }
    }

    fun deleteTask(task: Task, context: Context){
        TaskDatabase.getInstance(context).taskDao().deleteTask(task)
        mListTask = TaskDatabase.getInstance(context).taskDao().getAllTask()
        mListTaskData.postValue(mListTask)
    }

    fun getListTaskLiveData() = mListTaskData
    fun getListTaskDoneLiveData() = mStatusTaskDoneData

}