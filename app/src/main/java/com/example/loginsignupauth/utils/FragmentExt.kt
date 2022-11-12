package com.example.loginsignupauth.utils

import android.app.Activity
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.loginsignupauth.lottie.LoadingDialogFragment
import com.google.android.material.snackbar.Snackbar


val Fragment.viewLifecycleScope
    get() = viewLifecycleOwner.lifecycleScope

val Fragment.navController
    get() = findNavController()

fun Fragment.showSnackBar(
    snackBarText: String,
    timeLength: Int = Snackbar.LENGTH_LONG,
    topGravity: Boolean = false,
) {
    activity?.showSnackBar(snackBarText, timeLength, topGravity)
}

fun Activity.showSnackBar(
    snackBarText: String,
    timeLength: Int = Snackbar.LENGTH_LONG,
    topGravity: Boolean = true,
    cb: ((Snackbar) -> Unit)? = null,
) {
    this.let {
        val snack = Snackbar.make(it.findViewById(android.R.id.content), snackBarText, timeLength)
        val view = snack.view
        if (topGravity) {
            val params = view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            view.layoutParams = params
        }
        val tv = view.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
        tv.setTextColor(Color.WHITE)
        cb?.invoke(snack)
        snack.show()
    }
}


fun Fragment.showLoadingDialog() {
    showDialogFragment { LoadingDialogFragment() }
}

fun Fragment.hideLoadingDialog() {
    dismissDialog<LoadingDialogFragment>()
}

inline fun <reified T : DialogFragment> Fragment.showDialogFragment(
    tag: String = T::class.java.simpleName,
    crossinline init: () -> T,
) {
    childFragmentManager.showDialogFragment(tag, init)
}

inline fun <reified T : DialogFragment> FragmentManager.showDialogFragment(
    tag: String = T::class.java.simpleName,
    crossinline init: () -> T,
) {
    @Suppress("UNCHECKED_CAST")
    val dialogFrag = findFragmentByTag(tag) as? T
    if (dialogFrag?.dialog?.isShowing == true) return
    try {
        init().show(this, tag)
    } catch (ex: IllegalStateException) {
        // no-op
    }
}

inline fun <reified T : DialogFragment> Fragment.dismissDialog(tag: String = T::class.java.simpleName): Boolean {
    return childFragmentManager.dismissDialog<T>(tag)
}

inline fun <reified T : DialogFragment> FragmentManager.dismissDialog(tag: String = T::class.java.simpleName): Boolean {
    val dialog = findFragmentByTag(tag) as? T
    if (dialog?.dialog?.isShowing == true) {
        try {
            dialog.dismiss()
            return true
        } catch (ex: IllegalStateException) {

        }
    }
    return false
}