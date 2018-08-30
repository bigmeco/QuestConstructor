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
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bigmeco.questconstructor.R
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_creator_screen.*
import kotlinx.android.synthetic.main.fragment_creator_screen.*


class CreatorScreenFragment : Fragment() {

    val realm = Realm.getDefaultInstance()
    private var idProject =0
    private var idScreen =0
    private var objectProject: ObjectProject? = ObjectProject()
    private var objectScreen: ObjectScreen? = ObjectScreen()
        var objectButton: ArrayList<ObjectButton> = ArrayList()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_creator_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            idProject = bundle.getInt("project", 0)
            idScreen = bundle.getInt("screen", 0)
        }
        objectProject = realm.where(ObjectProject::class.java).equalTo("id", idProject).findFirst()
        objectScreen = objectProject?.screen?.get(idScreen)
        objectButton = ArrayList(objectScreen?.buttons)


        imageScreen.setOnClickListener{
Log.d("rrrrr",idScreen.toString())
        }
        listButtons.layoutManager = LinearLayoutManager(activity)
        listButtons.adapter = ButtonsAdapter(objectButton) {

            val set = ConstraintSet()
            set.clone(activity?.mainLayout)
            set.clear(activity!!.fading_edge_layout.id, ConstraintSet.TOP)
            set.connect(activity!!.fading_edge_layout.id, ConstraintSet.TOP,  ConstraintSet.PARENT_ID, ConstraintSet.TOP, 512)
            TransitionManager.beginDelayedTransition(activity!!.mainLayout)
            set.applyTo(activity!!.mainLayout)

        }
        buttonAdd.setOnClickListener{
            objectButton.add(ObjectButton())
            (listButtons.adapter as ButtonsAdapter).notifyItemInserted(objectButton.size)

        }

        mainLayout


    //    listButtonsUpdate(objectButton)



        val itemLeftHelper = ItemTouchHelper(simpleLeftCallback)
        itemLeftHelper.attachToRecyclerView(listButtons)
        val itemRightHelper = ItemTouchHelper(simpleRightCallback)
        itemRightHelper.attachToRecyclerView(listButtons)

    }


    override fun onStop() {
        super.onStop()
        val bundle = this.arguments

        Log.d("fffff", bundle?.getInt("IDscreen", 0).toString())

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
