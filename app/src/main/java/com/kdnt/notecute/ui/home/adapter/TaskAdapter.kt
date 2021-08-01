package com.kdnt.notecute.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kdnt.notecute.data.entity.Task
import com.kdnt.notecute.databinding.ItemTaskBinding

class TaskAdapter(private val mListTask: MutableList<Task>, private val listener: ITask) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    fun applyData(listNewTask: MutableList<Task>) {
        mListTask.clear()
        mListTask.addAll(listNewTask)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindData(mListTask[position], listener)
    }

    override fun getItemCount(): Int {
        return mListTask.size
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Task, listener: ITask) {
            binding.checkboxTask.isChecked = data.isCompleted
            binding.tvTask.text = data.name
            binding.tvTask.paint.isStrikeThruText = data.isCompleted
//            itemView.setOnClickListener { listener.onTaskClick(data) }
            binding.ivDelete.setOnClickListener { listener.onDeleteTaskClick(data) }
            binding.ivEdit.setOnClickListener { listener.onEditTaskClick(data) }
            binding.checkboxTask.setOnClickListener {
                listener.onCheckboxTaskClick(data, binding.checkboxTask.isChecked)
            }
        }
    }

    interface ITask {
        fun onEditTaskClick(task: Task)
        fun onDeleteTaskClick(task: Task)
        fun onTaskClick(task: Task)
        fun onCheckboxTaskClick(task: Task, isCompleted: Boolean)
    }
}