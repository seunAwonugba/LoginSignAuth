package com.example.loginsignupauth.utils

import android.content.Context
import android.text.InputFilter
import android.util.Patterns
import androidx.annotation.DimenRes
import com.google.android.material.textfield.TextInputEditText


fun String.isValidEmail(): Boolean = this.isNotEmpty() &&
        Patterns.EMAIL_ADDRESS.matcher(this).matches() && this.contains('@')

fun String.atLeastOneLowerCase() : Boolean {
    val pattern = "(.*[a-z].*)".toRegex()
    return this.matches(pattern)
}
fun String.atLeastOneUpperCase() : Boolean {
    val pattern = "(.*[A-Z].*)".toRegex()
    return this.matches(pattern)
}
fun String.atLeastOneDigit() : Boolean {
    val pattern = "(.*\\d.*)".toRegex()
    return this.matches(pattern)
}
fun String.atLeastOneSpecialCharacter() : Boolean {
    val pattern = ".*[~!@#\\\$%\\\\^&*()\\\\-_=+\\\\|\\\\[{\\\\]};:'\\\",<.>/?].*".toRegex()
    return this.matches(pattern)
}
fun String.atLeastEightCharacters() : Boolean {
    val pattern = ".{8,}".toRegex()
    return this.matches(pattern)
}

fun TextInputEditText.filterWhiteSpace(text : TextInputEditText){
    val filter = InputFilter { source, start, end, _, _, _ ->
        for (i in start until end) {
            if (Character.isWhitespace(source[i])) {
                return@InputFilter ""
            }
        }
        null
    }
    text.filters = arrayOf(filter)
}

fun Context.px(@DimenRes dimen: Int): Int = resources.getDimension(dimen).toInt()



//val filter = InputFilter { source, start, end, dest, dstart, dend ->
//    for (i in start until end) {
//        if (Character.isWhitespace(source[i])) {
//            return@InputFilter ""
//        }
//    }
//    null
//}
//binding.etPassword.filters = arrayOf(filter)