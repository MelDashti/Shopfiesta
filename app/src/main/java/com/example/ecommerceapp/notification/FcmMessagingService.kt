package com.example.ecommerceapp.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.example.ecommerceapp.R
import com.example.ecommerceapp.persistence.NotificationItem
import com.example.ecommerceapp.persistence.ProductDao
import com.example.ecommerceapp.util.sendNotification
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import javax.inject.Inject


@AndroidEntryPoint
class FcmMessagingService : FirebaseMessagingService() {
    @Inject
    lateinit var productDao: ProductDao
    override fun onCreate() {
        super.onCreate()
    }

    val job = Job()
    val coroutineScope = CoroutineScope(Dispatchers.Main + job)

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
// Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        val dateFormatter = SimpleDateFormat("dd/MM")
        val dateString = dateFormatter.format(remoteMessage?.sentTime)
        remoteMessage?.notification?.let {
            coroutineScope.launch(Dispatchers.IO) {
                productDao.insertNotification(
                    NotificationItem(
                        title = it.title!!,
                        body = it.body!!,
                        date = dateString
                    )
                )
            }
            sendNotification(it.title!!, it.body!!)
        }
    }


    override fun onNewToken(token: String) {
        FirebaseMessaging.getInstance().subscribeToTopic("all")
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String) {

    }

    private fun sendNotification(messageTitle: String, messageBody: String) {
        val notificationManager = ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = getString(R.string.shopfiesta_notification_channel_name)
            val descriptionText = getString(R.string.notification_channel_description)
            val channelID = getString(R.string.shopfiesta_notification_channel_id)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(channelID, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager.createNotificationChannel(mChannel)
        }

        notificationManager.sendNotification(messageTitle, messageBody, applicationContext)
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}