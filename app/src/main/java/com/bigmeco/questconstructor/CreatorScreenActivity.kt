package com.bigmeco.questconstructor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import kotlinx.android.synthetic.main.activity_creator_screen.*

class CreatorScreenActivity : AppCompatActivity() {

    val objectScreens = ArrayList<ObjectButton>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator_screen)
        objectScreens.add(ObjectButton())

        listButtons.layoutManager = LinearLayoutManager(this)!!
        listButtons.adapter = ButtonsAdapter(objectScreens){


        }
        buttonAdd.setOnClickListener{
            objectScreens.add(ObjectButton())
            (listButtons.adapter as ButtonsAdapter).notifyItemInserted(objectScreens.size)
        }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(listButtons)
    }

    private var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val position = viewHolder.adapterPosition
            objectScreens.removeAt(position)
            listButtons.adapter!!.notifyDataSetChanged()
        }
    }
}
