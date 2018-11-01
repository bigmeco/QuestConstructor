package com.bigmeco.questconstructor

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.Realm

import kotlinx.android.synthetic.main.item_button_add.view.*


class ButtonsGameAdapter(val items:ArrayList<ObjectButton>, val listener: (position: Int) -> Unit) : RecyclerView.Adapter<ButtonsGameAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var i = if (false){
            LayoutInflater.from(parent.context).inflate(R.layout.item_steampunk, parent, false)
        }
        else{
            LayoutInflater.from(parent.context).inflate(R.layout.item_cyberpunk, parent, false)
        }
       return ViewHolder(i)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener,items, position)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ObjectButton, listener: ( position: Int) -> Unit, items: ArrayList<ObjectButton>, position: Int) = with(itemView) {

        }
    }
}