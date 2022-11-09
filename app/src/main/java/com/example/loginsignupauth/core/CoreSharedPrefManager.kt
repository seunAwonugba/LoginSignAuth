package com.example.loginsignupauth.core

import android.content.Context
import android.content.SharedPreferences
import com.example.loginsignupauth.constants.Constants.CORE_SHARED_PREF_KEY
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
open class CoreSharedPrefManager @Inject constructor (@ApplicationContext context: Context) {

    private var coreSharedPref = context.getSharedPreferences(
        CORE_SHARED_PREF_KEY,
        Context.MODE_PRIVATE
    )

    private val editor: SharedPreferences.Editor = coreSharedPref.edit()

    fun deletePref(key: String) {
        if (coreSharedPref.contains(key)) {
            editor.remove(key).commit()
        }
    }


    fun savePref(key: String, value: Any?) {
        deletePref(key)

        when {
            value is Boolean -> editor.putBoolean(key, (value as Boolean?)!!)
            value is Int -> editor.putInt(key, (value as Int?)!!)
            value is Float -> editor.putFloat(key, (value as Float?)!!)
            value is Long -> editor.putLong(key, (value as Long?)!!)
            value is String -> editor.putString(key, value as String?)
            value is Set<*> -> editor.putStringSet(key, value as Set<String>)
            value is Enum<*> -> editor.putString(key, value.toString())
            value != null -> throw RuntimeException("Attempting to save non-primitive preference")
        }

        editor.commit()
    }

    fun <T> getPref(key: String): T? {
        return coreSharedPref.all[key] as? T
    }
}