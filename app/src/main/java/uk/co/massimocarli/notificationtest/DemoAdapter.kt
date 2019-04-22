package uk.co.massimocarli.notificationtest

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DemoAdapter : RecyclerView.Adapter<DemoViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(android.R.layout.simple_list_item_1, parent, false) as TextView
    return DemoViewHolder(view)
  }

  override fun getItemCount(): Int = MODEL.size

  override fun onBindViewHolder(holder: DemoViewHolder, position: Int) =
    holder.bindModel(MODEL[position])
}