package com.shbhack.eggmoneyna.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

object AppPreferences {
    private lateinit var preferences: SharedPreferences
    private val gson = GsonBuilder().create()

    private const val LOGIN_SESSION = "login.session"
    private const val SHOWED_ON_BOARDING = "showedOnBoarding"

    fun openSharedPreferences(context: Context) {
        preferences = context.getSharedPreferences(LOGIN_SESSION, Context.MODE_PRIVATE)
    }

    fun checkOnBoardingShowed() {
        preferences.edit().putBoolean(SHOWED_ON_BOARDING, true).apply()
    }

    fun isOnBoardingShowed() = preferences.getBoolean(SHOWED_ON_BOARDING, false)
}