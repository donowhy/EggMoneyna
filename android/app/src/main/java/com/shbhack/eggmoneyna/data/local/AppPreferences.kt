package com.shbhack.eggmoneyna.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

object AppPreferences {
    private lateinit var preferences: SharedPreferences
    private val gson = GsonBuilder().create()

    private const val LOGIN_SESSION = "login.session"
    private const val SHOWED_FIRST = "showedFirst"
    private const val IS_PARENT = "isParent"

    fun openSharedPreferences(context: Context) {
        preferences = context.getSharedPreferences(LOGIN_SESSION, Context.MODE_PRIVATE)
    }

    fun checkFirstShowed() {
        preferences.edit().putBoolean(SHOWED_FIRST, true).apply()
    }

    fun isFirstShowed() = preferences.getBoolean(SHOWED_FIRST, false)

    fun checkSelectParent(choose: Boolean) {
        preferences.edit().putBoolean(IS_PARENT, choose).apply()
    }

    fun isParent() = preferences.getBoolean(IS_PARENT, false)
}