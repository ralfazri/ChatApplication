package com.surelabs.indonesia.chatapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingHandleService : FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        if (p0.data.isNotEmpty()) {
            Log.d(this@MessagingHandleService::class.java.simpleName, p0.data.toString())
            NotificationHandle(this).makeNotificationStart()
        }
    }
}

class NotificationHandle(
    private val context: Context,
    private val id: Int = Math.random().toInt()
) {
    private val cHANNELID = "myChanel"

    init {
        createNotificationChannel()
    }

    fun makeNotificationStart() {
        val builder = NotificationCompat.Builder(context, cHANNELID)
            .setSmallIcon(android.R.drawable.ic_dialog_email)
            .setColor(context.resources.getColor(R.color.purple_700))
            .setContentTitle(context.getString(R.string.app_name))
            .setContentText("Uuuyyy... ada pesan masuk")
            .setPriority(NotificationCompat.PRIORITY_MAX)

        NotificationManagerCompat.from(context).apply {
            notify(id, builder.build())
        }

    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "lauwba_channel"
            val descriptionText = "lauwba_channel_desc"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(cHANNELID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}