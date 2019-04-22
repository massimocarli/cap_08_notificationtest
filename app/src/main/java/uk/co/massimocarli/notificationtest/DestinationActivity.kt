package uk.co.massimocarli.notificationtest

import android.app.Notification
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput

class DestinationActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_destination)
    intent?.getStringExtra(ACTION_RESULT)?.let {
      Toast.makeText(this@DestinationActivity, "Received: $it", Toast.LENGTH_SHORT).show()
    }
    manageReply()
  }


  private fun manageReply() {
    RemoteInput.getResultsFromIntent(intent)?.getCharSequence(ACTION_RESULT).let {
      Toast.makeText(this@DestinationActivity, "Received: $it", Toast.LENGTH_SHORT).show()
      val repliedNotification = Notification.Builder(this, CHANNEL_ID)
        .setSmallIcon(android.R.drawable.ic_menu_save)
        .setContentText(getString(R.string.replied_label))
        .build()
      NotificationManagerCompat.from(this).apply {
        notificationManager().notify(SIMPLE_WITH_INPUT_NOTIFICATION_ID, repliedNotification)
      }
    }
  }
}