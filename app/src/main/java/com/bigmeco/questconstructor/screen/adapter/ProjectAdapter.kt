package com.bigmeco.questconstructor.screen.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.R
import kotlinx.android.synthetic.main.item_project.view.*

class ProjectAdapter(val items: ArrayList<ObjectProject>, val listener: (itemView: View, items: ArrayList<ObjectProject>, position: Int) -> Unit) : RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener, items, position)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ObjectProject, listener: (itemView: View, items: ArrayList<ObjectProject>, position: Int) -> Unit, items: ArrayList<ObjectProject>, position: Int) = with(itemView) {
            if (item.status) {
                cardColor.setCardBackgroundColor(resources.getColor(R.color.createColor))
                if(item.releaseKey!="") {
                    pushButton.text ="ОБНОВИТЬ"
                }else{
                    pushButton.text ="ОПУБЛИКОВАТЬ"
                }
                cardPush.visibility =View.VISIBLE
            }else {
                cardColor.setCardBackgroundColor(resources.getColor(R.color.pleyColor))
                cardPush.visibility =View.GONE
            }
            textName.text =item.name
            editTextBody.text =item.body
            textGenres.text =item.genre
            textTime.text =item.time
            listener.invoke(itemView,items,position)

        }

    }
}
