package com.bigmeco.questconstructor

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_screen.view.*

class ScreenAdapter(val items: ArrayList<ObjectScreen>, val listener: (itemView: View) -> Unit) : RecyclerView.Adapter<ScreenAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_screen, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener, items, position)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ObjectScreen, listener: (itemView: View) -> Unit, items: ArrayList<ObjectScreen>, position: Int) = with(itemView) {
            textBody.text = item.body
            if (true) {
                colorState.setBackgroundColor(resources.getColor(R.color.createColor))
            }
        }

    }
}
