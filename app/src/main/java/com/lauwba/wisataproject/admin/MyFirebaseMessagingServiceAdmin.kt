package com.lauwba.wisataproject.admin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.lauwba.wisataproject.R
import com.lauwba.wisataproject.service.NotificationHandler

class MyFirebaseMessagingServiceAdmin : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        //check apakah pesan yang datang berisi notifikasi or  node data (node:kumpulan)

        if (p0.notification != null) {
            //berarti yg dikirimkan type notification
            //notification payload
            val notificationItem = NotificationHandler.NotificationItem()
            notificationItem.title = p0.notification?.title
            notificationItem.body = p0.notification?.body
            MakeNotification(this, notificationItem).sendNotification()
//            val body = p0.notification?.body
//            Toast.makeText(this, body, Toast.LENGTH_SHORT).show()
        } else {
            // berarti yg dikirimkan berupa data payload
        }

    }

    class MakeNotification(
        private val context: Context,
        private val item: NotificationHandler.NotificationItem
    ) { //yg akan berubah cuman parameter ke2 "private val NotificationItem"
        val channelId = System.nanoTime().toString()
        val notification = NotificationCompat.Builder(context, channelId)

        fun sendNotification() {
            notification.setContentTitle(item.title)
                .setContentText(item.body)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_HIGH) //ini untuk versi oreo kebawah
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)) //ini untuk ringtone
                .setAutoCancel(true)

            //ini untuk versi oreo keatas
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val chanelName = context.applicationContext.packageName.toString()
                val desc = context.applicationContext.packageName.toString()
                val notificationChannel =
                    NotificationChannel(channelId, chanelName, NotificationManager.IMPORTANCE_HIGH)
                        .apply {
                            description = desc
                        }

                val notif =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notif.createNotificationChannel(notificationChannel)
            }

            NotificationManagerCompat.from(context).apply {
                notify(1, notification.build())
            }
        }
    }


}
