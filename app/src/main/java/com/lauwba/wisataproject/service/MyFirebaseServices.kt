package com.lauwba.wisataproject.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseServices : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        if (p0.notification != null) {
            NotificationHandler().saldoReceived(this, p0.notification)
        }

//        Log.d("MESSAGE RECEIVE", p0.notification?.body.toString())
    }
}
