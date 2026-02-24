package com.ibadetapp.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.ibadetapp.R
import com.ibadetapp.ui.main.MainActivity

/**
 * Centralized notification management utility
 * Handles all app notifications: zikir completion, prayer times, reminders
 */
object NotificationManager {

    private const val CHANNEL_ID_ZIKIR = "ibadet_zikir_channel"
    private const val CHANNEL_ID_PRAYER = "ibadet_prayer_channel"
    private const val CHANNEL_ID_REMINDER = "ibadet_reminder_channel"

    private const val NOTIFICATION_ID_ZIKIR = 101
    private const val NOTIFICATION_ID_PRAYER = 102
    private const val NOTIFICATION_ID_REMINDER = 103

    /**
     * Creates notification channels for Android 8.0+
     * @param context Application context
     */
    fun createNotificationChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                    as android.app.NotificationManager

            // Zikir completion channel
            val zikirChannel = NotificationChannel(
                CHANNEL_ID_ZIKIR,
                "Zikir Bildirimleri",
                android.app.NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Zikir tamamlama ve başarı bildirimleri"
                enableVibration(true)
                setShowBadge(true)
            }
            notificationManager.createNotificationChannel(zikirChannel)

            // Prayer times channel
            val prayerChannel = NotificationChannel(
                CHANNEL_ID_PRAYER,
                "İbadet Zamanları",
                android.app.NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "İbadet ve namaz zamanı bildirimleri"
                enableVibration(true)
                setShowBadge(true)
            }
            notificationManager.createNotificationChannel(prayerChannel)

            // Reminder channel
            val reminderChannel = NotificationChannel(
                CHANNEL_ID_REMINDER,
                "Hatırlatıcılar",
                android.app.NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Günlük zikir hatırlatıcıları"
                enableVibration(false)
            }
            notificationManager.createNotificationChannel(reminderChannel)
        }
    }

    /**
     * Shows zikir completion notification
     * @param context Application context
     * @param zikirName Name of completed zikir
     * @param count Count completed
     */
    fun showZikirCompletionNotification(context: Context, zikirName: String, count: Int) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID_ZIKIR)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("🎉 Zikir Tamamlandı!")
            .setContentText("$zikirName - $count kez")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(longArrayOf(0, 250, 250, 250))
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                as android.app.NotificationManager
        notificationManager.notify(NOTIFICATION_ID_ZIKIR, notification)
    }

    /**
     * Shows prayer time notification
     * @param context Application context
     * @param prayerName Prayer name (Fajr, Dhuhr, etc.)
     * @param time Prayer time
     */
    fun showPrayerTimeNotification(context: Context, prayerName: String, time: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID_PRAYER)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("🕌 $prayerName Zamanı")
            .setContentText("Saat: $time")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                as android.app.NotificationManager
        notificationManager.notify(NOTIFICATION_ID_PRAYER, notification)
    }

    /**
     * Shows daily zikir reminder notification
     * @param context Application context
     */
    fun showDailyReminderNotification(context: Context) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID_REMINDER)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("📿 Günlük Zikir Zamanı")
            .setContentText("Bugünün zikirlerini tamamlamayı unutmayın")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                as android.app.NotificationManager
        notificationManager.notify(NOTIFICATION_ID_REMINDER, notification)
    }

    /**
     * Dismisses notification by ID
     * @param context Application context
     * @param notificationId ID of notification to dismiss
     */
    fun dismissNotification(context: Context, notificationId: Int) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                as android.app.NotificationManager
        notificationManager.cancel(notificationId)
    }

    /**
     * Dismisses all app notifications
     * @param context Application context
     */
    fun dismissAllNotifications(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                as android.app.NotificationManager
        notificationManager.cancelAll()
    }
}
