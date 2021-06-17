package com.example.ecommerceapp.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.ecommerceapp.MainActivity
import com.example.ecommerceapp.R

private var NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0
fun NotificationManager.sendNotification(
    messageTitle: String,
    messageBody: String,
    applicationContext: Context
) {

    val contentIntent = Intent(applicationContext, MainActivity::class.java)

    val contentPendingIntent2 = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    //Content action Intent
    val contentPendingIntent =
        NavDeepLinkBuilder(applicationContext).setGraph(R.navigation.main_nav_graph)
            .setDestination(R.id.notificationPageFragment)
            .createPendingIntent()


    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.shopfiesta_notification_channel_id)
    )
        .setSmallIcon(R.drawable.ic_shopping_bag)
        .setContentTitle(messageTitle)
        .setContentText(messageBody)
        .setContentIntent(contentPendingIntent2)
        .setAutoCancel(true)
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    notify(NOTIFICATION_ID, builder.build())
    //snooze action
}

fun NotificationManager.cancelNotifications() {
    cancelAll()
}