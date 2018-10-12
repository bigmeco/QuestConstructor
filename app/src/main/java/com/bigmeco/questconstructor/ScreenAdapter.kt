package com.bigmeco.questconstructor

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_screen.view.*

class ScreenAdapter(val items: List<ObjectScreen>, val listener: (position: Int) -> Unit) : RecyclerView.Adapter<ScreenAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_screen, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener, items, position)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ObjectScreen, listener: (position: Int) -> Unit, items: List<ObjectScreen>, position: Int) = with(itemView) {
            editTextBody.text = item.body
            if (item.status!!) {
                colorState.setBackgroundColor(resources.getColor(R.color.createColor))
            }
            cardScreen.setOnClickListener{
                listener.invoke(position)
            }
        }

    }
}
