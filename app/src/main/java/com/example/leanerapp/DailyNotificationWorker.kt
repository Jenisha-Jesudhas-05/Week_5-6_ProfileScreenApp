package com.example.leanerapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.*
import java.util.concurrent.TimeUnit

class DailyNotificationWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        // Create notification channel (API 26+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Daily Reminder",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = applicationContext
                .getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }

        // Check permission before notifying
        val hasPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                applicationContext,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true // Permission not required below Android 13
        }

        if (hasPermission) {
            val notification = NotificationCompat
                .Builder(applicationContext, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setContentTitle("LearnerApp")
                .setContentText("Don't forget to update your profile!")
                .setAutoCancel(true)
                .build()

            NotificationManagerCompat
                .from(applicationContext)
                .notify(NOTIFICATION_ID, notification)
        }

        return Result.success()
    }

    companion object {
        const val CHANNEL_ID = "daily_reminder_channel"
        const val NOTIFICATION_ID = 1001

        fun schedule(context: Context) {
            val request =
                PeriodicWorkRequestBuilder<DailyNotificationWorker>(
                    15, TimeUnit.MINUTES
                ).build()

            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(
                    "daily_notification_15min",
                    ExistingPeriodicWorkPolicy.UPDATE,
                    request
                )
        }
    }
}