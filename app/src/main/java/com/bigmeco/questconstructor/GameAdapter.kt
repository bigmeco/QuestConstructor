package com.bigmeco.questconstructor

import android.graphics.drawable.Animatable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_game.view.*
import android.animation.ObjectAnimator




class GameAdapter(val items: ArrayList<ObjectScreen>, val listener: (item: ObjectScreen, position: Int) -> Unit) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener, items, position)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ObjectScreen, listener: (item: ObjectScreen, position: Int) -> Unit, items: ArrayList<ObjectScreen>, position: Int) = with(itemView) {
//            mainItam.setOnClickListener {
//                listener.invoke(item,position)
//            }
//            if (item.body != null || item.body != "") {
//                editTextBody.text = item.body
//            }
//            if (item.status!!) {
//                cardState.setCardBackgroundColor(resources.getColor(R.color.createColor))
//            }
            imageStar.setImageResource(R.drawable.anim_star1)
            val drawable = imageStar.drawable
            if (drawable is Animatable) (drawable as Animatable).start()

        }

    }
}
