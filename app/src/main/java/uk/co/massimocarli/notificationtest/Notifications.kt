package uk.co.massimocarli.notificationtest

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

const val CHANNEL_ID = "MaxNotificationChannel"

fun Activity.createMaxNotificationChannel() {
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    // Create the NotificationChannel
    val name = getString(R.string.notification_channel_name)
    val descriptionText = getString(R.string.notification_channel_description)
    val importance = NotificationManager.IMPORTANCE_DEFAULT
    val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
    mChannel.description = descriptionText
    notificationManager().createNotificationChannel(mChannel)
  }
}

/**
 * Dumps the Notification information
 */
fun Context.dumpChannel(channelId: String) {
  val TAG = "CHANNEL_DUMP"
  val channel = notificationManager().getNotificationChannel(channelId)
  Log.d(TAG, "ID: ${channel.id} in Group ${channel.group} [${channel.description}]")
  Log.d(TAG, "Audio Attributes: ${channel.audioAttributes}")
  Log.d(TAG, "Importance: ${channel.importance}")
  Log.d(TAG, "Light Color: ${channel.lightColor}")
  Log.d(TAG, "Sound: ${channel.sound}")
}

fun Activity.launchChannelSettings(channelId: String) {
  val intent = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS).apply {
    putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
    putExtra(Settings.EXTRA_CHANNEL_ID, channelId)
  }
  startActivity(intent)
}

fun Context.deleteChannel(channelId: String) =
  notificationManager().deleteNotificationChannel(channelId)

fun Context.createChannelGroup(groupId: String, groupName: String) =
  notificationManager().createNotificationChannelGroup(
    NotificationChannelGroup(groupId, groupName)
  )

fun Context.notificationManager() =
  getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
