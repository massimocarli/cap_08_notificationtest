package uk.co.massimocarli.notificationtest

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    createMaxNotificationChannel()
    val linearLayoutManager = LinearLayoutManager(this)
    recyclerView.apply {
      layoutManager = linearLayoutManager
      adapter = DemoAdapter()
    }
  }


  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    MenuInflater(this).inflate(R.menu.menu, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.channel_settings_id -> launchChannelSettings(CHANNEL_ID)
      R.id.channel_dump_id -> dumpChannel(CHANNEL_ID)
      R.id.channel_delete_id -> deleteChannel(CHANNEL_ID)
      else -> {
      }
    }
    return super.onOptionsItemSelected(item)
  }
}


