package com.kdnt.notecute.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.kdnt.notecute.Constants
import com.kdnt.notecute.core.base.BaseActivity
import com.kdnt.notecute.data.database.TaskDatabase
import com.kdnt.notecute.data.entity.Task
import com.kdnt.notecute.R
import com.kdnt.notecute.databinding.ActivityHomeBinding
import com.kdnt.notecute.ui.edit.EditTaskActivity
import com.kdnt.notecute.ui.home.adapter.TaskAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>(),
    CustomBottomSheetDialog.IBottomSheetDialog {
    private var bottomSheetDialog: CustomBottomSheetDialog? = null
    private var mAdapter: TaskAdapter? = null
    private var mListTask = mutableListOf<Task>()

    override fun getLayoutResId(): Int = R.layout.activity_home
    override val mViewModel: HomeViewModel by viewModel()

    companion object {
        fun openActivity(context: Context): Intent = Intent(context, HomeActivity::class.java)
    }


    private val listener = object : TaskAdapter.ITask {
        override fun onEditTaskClick(task: Task) {
            editTask(task)
        }

        override fun onDeleteTaskClick(task: Task) {
            showDialogDeleteTask(task)
        }

        override fun onTaskClick(task: Task) {

        }

        override fun onCheckboxTaskClick(task: Task, isCompleted: Boolean) {
            mViewModel.onTaskCheckedChange(this@HomeActivity, task, isCompleted)
            if (isCompleted) {
                checkListTaskDone()
            } else {
                checkListTaskDone()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.MY_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            checkListTask()
        }
    }

    override fun onBackPressed() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDate()
        mAdapter = TaskAdapter(mListTask, listener)
        checkListTask()
        checkListTaskDone()
        mViewBinding.rcvIncomplete.layoutManager = LinearLayoutManager(this)
        mViewBinding.rcvIncomplete.adapter = mAdapter
        mViewBinding.fabAdd.setOnClickListener {
            showBottomSheetDialogAddTask()
        }

        mViewBinding.tvDeleteAll.setOnClickListener {
            deleteAllTask()
        }

        mViewModel.getListTaskLiveData().observe(this, {
            mAdapter?.applyData(it)
            checkListTask()
        })
    }


    @Suppress("DEPRECATION")
    private fun editTask(task: Task) {
        val intent = Intent(this, EditTaskActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(Constants.KEY_TASK, task)
        intent.putExtras(bundle)
        startActivityForResult(intent, Constants.MY_REQUEST_CODE)
    }

    private fun deleteAllTask() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.title_all_dialog))
        builder.setMessage(getString(R.string.content_all_task))
        builder.setIcon(R.drawable.ic_baseline_warning_24)

        builder.setPositiveButton(android.R.string.ok) { _, _ ->
            Toast.makeText(this, "Deleted All", Toast.LENGTH_SHORT).show()
            mViewModel.deleteAllTask(this)
            checkListTask()
            checkListTaskDone()
            mAdapter?.applyData(mListTask)
        }

        builder.setNegativeButton(android.R.string.cancel) { _, _ ->
            Toast.makeText(
                applicationContext,
                android.R.string.cancel, Toast.LENGTH_SHORT
            ).show()
        }
        builder.show()
    }

    @SuppressLint("SimpleDateFormat")
    fun getDate() {
        val mDate = Calendar.getInstance().time
        val formatter = SimpleDateFormat("EEEE, dd/MM/yyyy")
        val currentDate = formatter.format(mDate)
        mViewBinding.tvDate.text = currentDate
    }

    //
    @SuppressLint("SetTextI18n")
    private fun checkListTask() {
        mListTask = TaskDatabase.getInstance(this).taskDao().getAllTask()
//        Log.d("aaa", TaskDatabase.getInstance(this).taskDao().getAllTask().toString() + "\n")
        mAdapter?.applyData(mListTask)
        if (mListTask.isEmpty()) {
            mViewBinding.noDataImage.visibility = View.VISIBLE
            mViewBinding.noDataText.visibility = View.VISIBLE
            mViewBinding.tvDeleteAll.visibility = View.INVISIBLE
            mViewBinding.tvTotalTask.text = "0 ${getString(R.string.tv_total_task)}"
        } else {
            mViewBinding.noDataImage.visibility = View.INVISIBLE
            mViewBinding.noDataText.visibility = View.INVISIBLE
            mViewBinding.tvDeleteAll.visibility = View.VISIBLE
            mViewBinding.tvTotalTask.text =
                mListTask.size.toString() + " ${getString(R.string.tv_total_task)}"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun checkListTaskDone() {
        val listTaskDone = mViewModel.getAllTaskDone(this)
        if (listTaskDone.isEmpty()) {
            mViewBinding.tvCompleted.text = "0 ${getString(R.string._3_complete)}"
        } else {
            mViewBinding.tvCompleted.text =
                listTaskDone.size.toString() + " ${getString(R.string._3_complete)}"
        }
    }

    private fun showDialogDeleteTask(task: Task) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.title_dialog))
        builder.setMessage(getString(R.string.content))
        builder.setIcon(R.drawable.ic_baseline_warning_24)

        builder.setPositiveButton(android.R.string.ok) { _, _ ->
            mViewModel.deleteTask(task, this)
            checkListTaskDone()
            Toast.makeText(
                applicationContext,
                "Đã xoá", Toast.LENGTH_SHORT
            ).show()
        }

        builder.setNegativeButton(android.R.string.cancel) { _, _ ->
            Toast.makeText(
                applicationContext,
                android.R.string.cancel, Toast.LENGTH_SHORT
            ).show()
        }
        builder.show()
    }

    private fun showBottomSheetDialogAddTask() {
        bottomSheetDialog = CustomBottomSheetDialog()
        bottomSheetDialog?.show(supportFragmentManager, CustomBottomSheetDialog.TAG)
    }

    override fun onSaveClick(text: String) {
        val task = Task(name = text)
        mViewModel.addTask(task, this)
    }
}