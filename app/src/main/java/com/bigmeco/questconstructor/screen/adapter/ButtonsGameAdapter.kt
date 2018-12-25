package com.bigmeco.questconstructor.screen.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bigmeco.questconstructor.R
import com.bigmeco.questconstructor.data.Button


class ButtonsGameAdapter(val idSt: Int, val items: ArrayList<Button>, val listener: (item: Button) -> Unit) : RecyclerView.Adapter<ButtonsGameAdapter.ViewHolder>() {
    var view = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var i = when (idSt) {
            0 -> LayoutInflater.from(parent.context).inflate(R.layout.item_cyberpunk, parent, false)
            1 -> LayoutInflater.from(parent.context).inflate(R.layout.item_steampunk, parent, false)
            2 -> LayoutInflater.from(parent.context).inflate(R.layout.item_fantasy, parent, false)
            3 -> LayoutInflater.from(parent.context).inflate(R.layout.item_horror, parent, false)
            else -> LayoutInflater.from(parent.context).inflate(R.layout.item_cyberpunk, parent, false)
        }
        return ViewHolder(i)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener, items, position)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Button, listener: (item: Button) -> Unit, items: ArrayList<Button>, position: Int) = with(itemView) {
            val textBodyCard = findViewById<TextView>(R.id.textBodyCard)
            val view = findViewById<View>(R.id.viewCard)
            textBodyCard.text = item.text
            view.setOnClickListener {listener.invoke(item) }
        }
    }
}