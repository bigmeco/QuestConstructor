package com.bigmeco.questconstructor

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bigmeco.questconstructor.R
import kotlinx.android.synthetic.main.fragment_creator_screen.*


class CreatorScreenFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_creator_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            val i = bundle.getInt("screen", 1)
        }
        imageScreen.setOnClickListener{

        }

        buttonAdd.setOnClickListener{
//            objectButton.add(ObjectButton())
//            (listButtons.adapter as ButtonsAdapter).notifyItemInserted(objectButton.size)
        }

        mainLayout


        listButtons.layoutManager = LinearLayoutManager(activity)
    //    listButtonsUpdate(objectButton)



        val itemLeftHelper = ItemTouchHelper(simpleLeftCallback)
        itemLeftHelper.attachToRecyclerView(listButtons)
        val itemRightHelper = ItemTouchHelper(simpleRightCallback)
        itemRightHelper.attachToRecyclerView(listButtons)

    }






private fun listButtonsUpdate(buttons: ArrayList<ObjectButton>) {
    listButtons.adapter = ButtonsAdapter(buttons) {
//        val set = ConstraintSet()
//        set.clone(mainLayout)
//        set.clear(fading_edge_layout.id, ConstraintSet.TOP)
//        set.connect(fading_edge_layout.id, ConstraintSet.TOP, cardBody.id, ConstraintSet.BOTTOM, 0)
//        TransitionManager.beginDelayedTransition(mainLayout)
//        set.applyTo(mainLayout)
    }
}



private var simpleLeftCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
        val position = viewHolder.adapterPosition
        //objectButton.removeAt(position)
        listButtons.adapter!!.notifyDataSetChanged()
    }
}
private var simpleRightCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
        listButtons.adapter!!.notifyDataSetChanged()

    }
}

}
