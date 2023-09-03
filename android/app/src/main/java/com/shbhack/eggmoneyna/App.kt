package com.shbhack.eggmoneyna

import android.app.Application
import com.shbhack.eggmoneyna.data.local.AppPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        AppPreferences.openSharedPreferences(applicationContext)
        super.onCreate()
    }
}