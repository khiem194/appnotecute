package com.kdnt.notecute.ui.edit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.kdnt.notecute.Constants
import com.kdnt.notecute.core.base.BaseActivity
import com.kdnt.notecute.data.entity.Task
import com.kdnt.notecute.ui.home.HomeViewModel
import com.kdnt.notecute.R
import com.kdnt.notecute.databinding.ActivityEditTaskBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditTaskActivity : BaseActivity<HomeViewModel, ActivityEditTaskBinding>() {
    private var mTask: Task? = null

    override fun getLayoutResId() = R.layout.activity_edit_task
    override val mViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "Edit Task"

        intent.getParcelableExtra<Task>(Constants.KEY_TASK).also { mTask = it }
        mViewBinding.edtEditTask.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 1)
        mViewBinding.edtEditTask.setText(mTask?.name)
        mViewBinding.edtEditTask.setSelection(mViewBinding.edtEditTask.text.length)
        mViewBinding.btnEditSave.setOnClickListener {
            updateTask()
        }
    }

    private fun updateTask() {
        val nameTask = mViewBinding.edtEditTask.text.toString().trim()
        if (TextUtils.isEmpty(nameTask)) {
            return Toast.makeText(this, getString(R.string.attention), Toast.LENGTH_SHORT).show()
        }
        mTask?.name = nameTask
        mTask?.let { mViewModel.editTask(this, it) }
        Toast.makeText(this, "Edit Successfully", Toast.LENGTH_SHORT).show()

        val intent = Intent()
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}