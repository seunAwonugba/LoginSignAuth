package com.example.loginsignupauth.lottie

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.airbnb.lottie.LottieAnimationView
import com.example.loginsignupauth.R

class LoadingDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(requireContext()) {
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setCancelable(false)
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(R.layout.dialog_loading)
                val lottieAnimationView = findViewById<LottieAnimationView>(R.id.loading_lottie)
                lottieAnimationView.setMinAndMaxFrame(0, 20)
                lottieAnimationView.enableMergePathsForKitKatAndAbove(true)
            }
        }
    }
}