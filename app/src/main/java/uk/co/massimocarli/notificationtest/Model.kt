package uk.co.massimocarli.notificationtest

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.app.RemoteInput
import java.util.function.Consumer
import kotlin.concurrent.thread

class DemoModel(val name: String, val action: Consumer<Context>)

const val SIMPLE_NOTIFICATION_ID = 1
const val BIG_TEXT_NOTIFICATION_ID = 2
const val BIG_PICTURE_NOTIFICATION_ID = 3
const val BIG_PICTURE_PREVIEW_NOTIFICATION_ID = 4
const val INBOX_NOTIFICATION_ID = 5
const val MESSAGING_NOTIFICATION_ID = 6
const val SIMPLE_PENDING_NOTIFICATION_ID = 7
const val SIMPLE_ACTION_NOTIFICATION_ID = 8
const val SIMPLE_WITH_INPUT_NOTIFICATION_ID = 9
const val SIMPLE_BACKSTACK_NOTIFICATION_ID = 10
const val SIMPLE_PROGRESSBAR_NOTIFICATION_ID = 11
const val SIMPLE_BADGE_COUNTER_NOTIFICATION_ID = 12
const val SIMPLE_SMALL_BADGE_NOTIFICATION_ID = 13

const val ACTION_RESULT = "ACTION_RESULT"
const val CONVERSATION_ID = 37

val showSimpleNotification: Consumer<Context> =
  Consumer { context: Context ->
    var builder = NotificationCompat.Builder(context, CHANNEL_ID)
      .setSmallIcon(R.drawable.ic_stat_face)
      .setContentTitle("Simple Text")
      .setContentText("This is the simple content")
      .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    context.notificationManager().notify(SIMPLE_NOTIFICATION_ID, builder.build())
  }

val showBigTextNotification: Consumer<Context> =
  Consumer { context: Context ->
    var builder = NotificationCompat.Builder(context, CHANNEL_ID)
      .setSmallIcon(R.drawable.ic_stat_face)
      .setContentTitle("Big Text")
      .setContentText("This is the Big Text notification")
      .setStyle(
        NotificationCompat.BigTextStyle()
          .bigText(context.getString(R.string.big_text))
      ).setPriority(NotificationCompat.PRIORITY_DEFAULT)
    context.notificationManager().notify(BIG_TEXT_NOTIFICATION_ID, builder.build())
  }

val showBigPictureNotification: Consumer<Context> =
  Consumer { context: Context ->
    val bitmap = BitmapFactory.decodeResource(
      context.resources,
      R.drawable.notification
    )
    var builder = NotificationCompat.Builder(context, CHANNEL_ID)
      .setSmallIcon(R.drawable.ic_stat_face)
      .setContentTitle("Big Picture")
      .setContentText("This is the Big Picture notification")
      .setStyle(
        NotificationCompat.BigPictureStyle()
          .bigPicture(bitmap)
      ).setPriority(NotificationCompat.PRIORITY_DEFAULT)
    context.notificationManager()
      .notify(BIG_PICTURE_NOTIFICATION_ID, builder.build())
  }

val showBigPicturePreviewNotification: Consumer<Context> =
  Consumer { context: Context ->
    val bitmap = BitmapFactory.decodeResource(
      context.resources,
      R.drawable.notification
    )
    var builder = NotificationCompat.Builder(context, CHANNEL_ID)
      .setSmallIcon(R.drawable.ic_stat_face)
      .setContentTitle("Big Picture")
      .setContentText("This is the Big Picture notification")
      .setLargeIcon(bitmap)
      .setStyle(
        NotificationCompat.BigPictureStyle()
          .bigPicture(bitmap)
          .bigLargeIcon(null)
      ).setPriority(NotificationCompat.PRIORITY_DEFAULT)
    context.notificationManager()
      .notify(BIG_PICTURE_PREVIEW_NOTIFICATION_ID, builder.build())
  }

val showInBoxNotification: Consumer<Context> =
  Consumer { context: Context ->
    var builder = NotificationCompat.Builder(context, CHANNEL_ID)
      .setSmallIcon(R.drawable.ic_stat_face)
      .setContentTitle("InBox Notification")
      .setContentText("This is the InBox notification")
      .setStyle(
        NotificationCompat.InboxStyle()
          .addLine("This is the First line")
          .addLine("This is the Second line")
          .addLine("This is the Third line")
      ).setPriority(NotificationCompat.PRIORITY_DEFAULT)
    context.notificationManager()
      .notify(INBOX_NOTIFICATION_ID, builder.build())
  }

val showMessagingNotification: Consumer<Context> =
  Consumer { context: Context ->
    var message1 = NotificationCompat.MessagingStyle.Message(
      "Hello, How are you?",
      System.currentTimeMillis(),
      "Mum"
    )
    var message2 = NotificationCompat.MessagingStyle.Message(
      "Fine Thanks and you?",
      System.currentTimeMillis(),
      "Max"
    )
    var message3 = NotificationCompat.MessagingStyle.Message(
      "All right! Thanks",
      System.currentTimeMillis(),
      "Mum"
    )
    val person = Person.Builder()
      .setName("Max")
      .build()
    var builder = NotificationCompat.Builder(context, CHANNEL_ID)
      .setSmallIcon(R.drawable.ic_stat_face)
      .setContentTitle("InBox Notification")
      .setContentText("This is the InBox notification")
      .setStyle(
        NotificationCompat.MessagingStyle(person)
          .addMessage(message1)
          .addMessage(message2)
          .addMessage(message3)
      )
      .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    context.notificationManager()
      .notify(MESSAGING_NOTIFICATION_ID, builder.build())
    PendingIntent.FLAG_IMMUTABLE
  }

val showSimpleWithPendingIntentNotification: Consumer<Context> =
  Consumer { context: Context ->
    val intent = Intent(context, DestinationActivity::class.java).apply {
      flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent =
      PendingIntent.getActivity(context, 0, intent, 0)
    var builder = NotificationCompat.Builder(context, CHANNEL_ID)
      .setSmallIcon(R.drawable.ic_stat_face)
      .setContentTitle("Simple Text With Launch")
      .setContentText("Click to launch DestinationActivity")
      .setPriority(NotificationCompat.PRIORITY_DEFAULT)
      .setContentIntent(pendingIntent)
      .setAutoCancel(true)
    context.notificationManager().notify(SIMPLE_PENDING_NOTIFICATION_ID, builder.build())
  }

val showSimpleWithActionsIntentNotification: Consumer<Context> =
  Consumer { context: Context ->
    val yesLabel = context.getString(android.R.string.yes)
    val noLabel = context.getString(android.R.string.no)
    val yesIntent = Intent(context, DestinationActivity::class.java).apply {
      flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
      putExtra(ACTION_RESULT, yesLabel)
    }
    val noIntent = Intent(context, DestinationActivity::class.java).apply {
      flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
      putExtra(ACTION_RESULT, noLabel)
    }
    val yesPendingIntent: PendingIntent =
      PendingIntent.getActivity(context, 1, yesIntent, 0)
    val noPendingIntent: PendingIntent =
      PendingIntent.getActivity(context, 2, noIntent, 0)
    var builder = NotificationCompat.Builder(context, CHANNEL_ID)
      .setSmallIcon(R.drawable.ic_stat_face)
      .setContentTitle("Simple Text With Actions")
      .setContentText("Click on the Action")
      .setPriority(NotificationCompat.PRIORITY_DEFAULT)
      .addAction(R.drawable.ic_stat_yes, yesLabel, yesPendingIntent)
      .addAction(R.drawable.ic_stat_no, noLabel, noPendingIntent)
      .setAutoCancel(true)
    context.notificationManager()
      .notify(SIMPLE_ACTION_NOTIFICATION_ID, builder.build())
  }

val showSimpleWithReplyIntentNotification: Consumer<Context> =
  Consumer { context: Context ->
    // We create the Intent
    val replyIntent = Intent(context, DestinationActivity::class.java).apply {
      flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    // We create the PendingIntent
    val replyPendingIntent: PendingIntent =
      PendingIntent.getActivity(
        context,
        CONVERSATION_ID,
        replyIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
      )
    // We create the remoteInput object
    val remoteInput: RemoteInput = RemoteInput.Builder(ACTION_RESULT).run {
      setLabel(context.getString(R.string.reply_label))
      build()
    }
    // We create the Action
    val replyAction = NotificationCompat.Action.Builder(
      R.drawable.ic_stat_reply,
      context.getString(R.string.reply_label), replyPendingIntent
    ).addRemoteInput(remoteInput)
      .build()
    var builder = NotificationCompat.Builder(context, CHANNEL_ID)
      .setSmallIcon(R.drawable.ic_stat_face)
      .setContentTitle("Simple Text With Actions")
      .setContentText("Click on the Action")
      .setPriority(NotificationCompat.PRIORITY_DEFAULT)
      .addAction(replyAction)
      .setAutoCancel(true)
    context.notificationManager()
      .notify(SIMPLE_WITH_INPUT_NOTIFICATION_ID, builder.build())
  }

val showSimpleWithBackStackNotification: Consumer<Context> =
  Consumer { context: Context ->
    val intent = Intent(context, DestinationActivity::class.java)
    val pendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
      addNextIntentWithParentStack(intent)
      getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
    }
    var builder = NotificationCompat.Builder(context, CHANNEL_ID)
      .setSmallIcon(R.drawable.ic_stat_face)
      .setContentTitle("Simple With BackStack")
      .setContentText("This is the simple with backstack")
      .setPriority(NotificationCompat.PRIORITY_DEFAULT)
      .setContentIntent(pendingIntent)
    context.notificationManager().notify(SIMPLE_BACKSTACK_NOTIFICATION_ID, builder.build())
  }

val showSimpleWithProgressBarNotification: Consumer<Context> =
  Consumer { context: Context ->
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
      .setSmallIcon(R.drawable.ic_stat_face)
      .setContentTitle("Simple With BackStack")
      .setContentText("This is the simple with backstack")
      .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    // We send notification with the ProgressBar with initial value
    NotificationManagerCompat.from(context).apply {
      builder.setProgress(100, 0, false)
      notify(SIMPLE_PROGRESSBAR_NOTIFICATION_ID, builder.build())
      thread {
        (0 until 100).forEach {
          Thread.sleep(200)
          builder.setProgress(100, it, false)
          notify(SIMPLE_PROGRESSBAR_NOTIFICATION_ID, builder.build())
        }
        builder.setContentText("Download complete")
          .setProgress(0, 0, false)
        notify(SIMPLE_PROGRESSBAR_NOTIFICATION_ID, builder.build())
      }
    }
  }

val showSimpleWithBadgeCountNotification: Consumer<Context> =
  Consumer { context: Context ->
    var builder = NotificationCompat.Builder(context, CHANNEL_ID)
      .setSmallIcon(R.drawable.ic_stat_face)
      .setContentTitle("Simple Text")
      .setContentText("This is the simple content")
      .setPriority(NotificationCompat.PRIORITY_DEFAULT)
      .setNumber(28)
    context.notificationManager()
      .notify(SIMPLE_BADGE_COUNTER_NOTIFICATION_ID, builder.build())
  }

val showSimpleWithSmallBadgeCountNotification: Consumer<Context> =
  Consumer { context: Context ->
    var builder = NotificationCompat.Builder(context, CHANNEL_ID)
      .setSmallIcon(R.drawable.ic_stat_face)
      .setContentTitle("Simple Text")
      .setContentText("This is the simple content")
      .setPriority(NotificationCompat.PRIORITY_DEFAULT)
      .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
    context.notificationManager()
      .notify(SIMPLE_SMALL_BADGE_NOTIFICATION_ID, builder.build())
  }


val MODEL = mutableListOf<DemoModel>().apply {
  add(DemoModel("Simple Notification", showSimpleNotification))
  add(DemoModel("Big Text Notification", showBigTextNotification))
  add(DemoModel("Big Picture Notification", showBigPictureNotification))
  add(DemoModel("Big Preview Picture Notification", showBigPicturePreviewNotification))
  add(DemoModel("InBox Notification", showInBoxNotification))
  add(DemoModel("Simple with PendingIntent", showSimpleWithPendingIntentNotification))
  add(DemoModel("Simple with Actions", showSimpleWithActionsIntentNotification))
  add(DemoModel("Simple with Reply", showSimpleWithReplyIntentNotification))
  add(DemoModel("Simple with Back Stack", showSimpleWithBackStackNotification))
  add(DemoModel("Simple with ProgressBar", showSimpleWithProgressBarNotification))
  add(DemoModel("Simple with BadgeCount", showSimpleWithBadgeCountNotification))
  add(DemoModel("Simple with Small Badge", showSimpleWithSmallBadgeCountNotification))
}
