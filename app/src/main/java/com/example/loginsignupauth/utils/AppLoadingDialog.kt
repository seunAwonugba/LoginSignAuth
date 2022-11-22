package com.example.loginsignupauth.utils

import android.app.Dialog
import android.os.Bundle
import android.util.TypedValue
import android.view.Window
import androidx.fragment.app.DialogFragment


class AppLoadingDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(requireContext()) {
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setCancelable(false)
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(com.example.loginsignupauth.R.layout.app_loader)

                val width = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    resources.getDimension(com.example.loginsignupauth.R.dimen.one_twenty_eight_dp),
                    resources.displayMetrics
                ).toInt()
                val height = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    resources.getDimension(com.example.loginsignupauth.R.dimen.one_twenty_eight_dp),
                    resources.displayMetrics
                ).toInt()
                dialog?.window?.setLayout(width, height)
            }
        }
    }
}