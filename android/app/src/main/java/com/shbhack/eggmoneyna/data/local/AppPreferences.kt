package com.shbhack.eggmoneyna.data.local

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.GsonBuilder

private const val TAG = "AppPreferences_신한"

object AppPreferences {
    private lateinit var preferences: SharedPreferences
    private val gson = GsonBuilder().create()

    private const val LOGIN_SESSION = "login.session"
    private const val SHOWED_FIRST = "showedFirst"
    private const val IS_PARENT = "isParent"

    private const val TOKEN = "token"

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

    fun initToken(token: String) {
        Log.d(TAG, "initToken: $token")
        preferences.edit().putString(TOKEN, token)
            .apply()
    }

    // sharedPreferences에 저장된 정보 반환
    fun getToken(): String? {
        return preferences.getString(TOKEN, "")
    }

}