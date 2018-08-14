package com.bigmeco.questconstructor

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ScreenAdapter(val items: List<ObjectScreen>, val listener: (itemView: View) -> Unit) : RecyclerView.Adapter<ScreenAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_screen, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener, items, position)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ObjectScreen, listener: (itemView: View) -> Unit, items: List<ObjectScreen>, position: Int) = with(itemView) {


        }

    }
}
