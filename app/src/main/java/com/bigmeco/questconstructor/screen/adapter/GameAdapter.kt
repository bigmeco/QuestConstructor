package com.bigmeco.questconstructor.screen.adapter

import android.graphics.drawable.Animatable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_game.view.*
import com.bigmeco.questconstructor.data.ObjectScreen
import com.bigmeco.questconstructor.R
import com.bigmeco.questconstructor.collections.RatingModel
import com.bigmeco.questconstructor.data.InfoProject


class GameAdapter(val items: ArrayList<InfoProject>, val listener: (item: InfoProject, position: Int) -> Unit) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener, items, position)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: InfoProject, listener: (item: InfoProject, position: Int) -> Unit, items: ArrayList<InfoProject>, position: Int) = with(itemView) {
            textName.text =item.name
            textTime.text =item.time
            textGenre.text =item.genre

            mainLayout.setOnClickListener {
                listener.invoke(item,position)
            }

            imageStar.setOnClickListener{

            }
            for (id in RatingModel.values()) {
                if (item.rating==id.size)
                    imageStar.setImageResource(id.image)
            }
            val drawable = imageStar.drawable
            if (drawable is Animatable) (drawable as Animatable).start()

        }

    }
}
