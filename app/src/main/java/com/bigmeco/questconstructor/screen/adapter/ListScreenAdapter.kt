package com.bigmeco.questconstructor.screen.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bigmeco.questconstructor.data.ObjectScreen
import com.bigmeco.questconstructor.R
import kotlinx.android.synthetic.main.item_sreen_add.view.*


class ListScreenAdapter(val items: ArrayList<ObjectScreen>, val listener: (item: ObjectScreen, position: Int) -> Unit) : RecyclerView.Adapter<ListScreenAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_sreen_add, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener, items, position)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ObjectScreen, listener: (item: ObjectScreen, position: Int) -> Unit, items: ArrayList<ObjectScreen>, position: Int) = with(itemView) {
            mainItam.setOnClickListener {
                listener.invoke(item,position)
            }
            if (item.body != null || item.body != "") {
                editTextBody.text = item.body
            }
            if (item.status!!) {
                cardState.setCardBackgroundColor(resources.getColor(R.color.createColor))
            }
        }

    }
}
