package com.bigmeco.questconstructor

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.item_button_add.view.*

class ButtonsAdapter(val items:ArrayList<ObjectButton>, val listener: (position: Int) -> Unit) : RecyclerView.Adapter<ButtonsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_button_add, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener,items, position)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ObjectButton, listener: ( position: Int) -> Unit, items: ArrayList<ObjectButton>, position: Int) = with(itemView) {
            buttenNextScreen.setOnClickListener {
                listener.invoke(position)
            }
            itemView.editBody.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable) {
                    item.text = itemView.editBody.text.toString()
                }

                override fun beforeTextChanged(s: CharSequence, start: Int,
                                               count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int,
                                           before: Int, count: Int) {
                }
            })

        }
    }
}