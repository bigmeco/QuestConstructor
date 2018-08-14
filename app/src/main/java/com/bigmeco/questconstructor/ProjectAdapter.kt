package com.bigmeco.questconstructor

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_project.view.*

class ProjectAdapter(val items: List<ObjectProject>, val listener: (itemView: View,items: List<ObjectProject>, position: Int) -> Unit) : RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener, items, position)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ObjectProject, listener: (itemView: View,items: List<ObjectProject>, position: Int) -> Unit, items: List<ObjectProject>, position: Int) = with(itemView) {
            listener.invoke(itemView,items,position)

        }

    }
}
