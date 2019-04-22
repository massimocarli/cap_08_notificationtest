package uk.co.massimocarli.notificationtest

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * This is the ViewHolder which runs the Consumers for a given model
 */
class DemoViewHolder(val view: TextView) : RecyclerView.ViewHolder(view) {

  private var localModel: DemoModel? = null

  init {
    view.setOnClickListener {
      localModel?.action?.accept(view.context)
    }
  }

  fun bindModel(model: DemoModel) {
    localModel = model
    view.text = model.name
  }
}
