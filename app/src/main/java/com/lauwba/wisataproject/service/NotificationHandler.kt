package com.lauwba.wisataproject.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.RemoteMessage
import com.lauwba.wisataproject.R

class NotificationHandler {

    private val CHANNELID = System.nanoTime().toString()
    fun saldoReceived(
        context: Context,
        notification: RemoteMessage.Notification?
    ) {
        val notificationCompat = NotificationCompat.Builder(context, CHANNELID)

        notificationCompat.priority = NotificationCompat.PRIORITY_HIGH
        notificationCompat.setContentText(notification?.body)
        notificationCompat.setContentTitle(notification?.title)
        notificationCompat.setAutoCancel(true)
        notificationCompat.setSmallIcon(R.mipmap.ic_launcher)
        notificationCompat.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = context.applicationContext.packageName.toString()
            val description = context.applicationContext.packageName.toString()
            val channel = NotificationChannel(
                CHANNELID,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                setDescription(description)
            }

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        NotificationManagerCompat.from(context).apply {
            notify(Math.random().toInt(), notificationCompat.build())
        }
    }

    class NotificationItem {

        var title: String? = null
        var body: String? = null
    }

    class NotificationFcmModel {
        var to: String? = null
        var notification: NotificationItem? = null
    }
}