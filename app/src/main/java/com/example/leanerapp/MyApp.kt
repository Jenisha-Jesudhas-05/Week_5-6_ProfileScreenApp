package com.example.leanerapp

import android.app.Application
import com.example.leanerapp.DailyNotificationWorker
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        
        try {
            DailyNotificationWorker.schedule(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
