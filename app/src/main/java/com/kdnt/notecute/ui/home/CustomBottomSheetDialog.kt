package com.kdnt.notecute.ui.home

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kdnt.notecute.databinding.DialogBottomSheetInputTaskBinding


class CustomBottomSheetDialog : BottomSheetDialogFragment(), View.OnClickListener {
    private var binding: DialogBottomSheetInputTaskBinding? = null
    private var mListener: IBottomSheetDialog? = null

    companion object {
        const val TAG = "CustomBottomSheetDialog"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = if (context is IBottomSheetDialog) {
            context
        } else {
            throw RuntimeException("$context must implement ItemClickListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { it ->
                val behaviour = BottomSheetBehavior.from(it)
                setupFullHeight(it)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    private fun setupFullHeight(bottom: View) {
        val layoutParams = bottom.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottom.layoutParams = layoutParams
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnSave?.setOnClickListener(this)
        binding?.edtTask?.requestFocus()
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogBottomSheetInputTaskBinding.inflate(inflater, container, false)
        binding?.ivClear?.setOnClickListener {
            dismiss()
        }
        return binding?.root
    }

    interface IBottomSheetDialog {
        fun onSaveClick(text: String)
    }

    override fun onClick(v: View?) {
        mListener?.onSaveClick(binding?.edtTask?.text.toString().trim())
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        dismiss()
    }
}