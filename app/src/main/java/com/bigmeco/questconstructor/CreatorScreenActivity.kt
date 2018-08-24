package com.bigmeco.questconstructor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import kotlinx.android.synthetic.main.activity_creator_screen.*
import android.content.Intent
import android.view.KeyEvent
import android.preference.PreferenceManager
import android.graphics.drawable.Animatable
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import io.realm.Realm


class CreatorScreenActivity : AppCompatActivity() {

    val objectScreens = ArrayList<ObjectButton>()
    val realm = Realm.getDefaultInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator_screen)
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        var id = preferences.getInt("idProject", 0)
        var idVoter = false
        val drawable = imageVoter.drawable

        if (drawable is Animatable) {
            (drawable as Animatable).start()
        }

        //
        var obP= realm.where(ObjectProject::class.java).equalTo("id",id).findFirst()
        if (obP != null) {
            for (i in 0..obP.screen!!.size)
                obP.screen
        }
        listScreen.layoutManager = LinearLayoutManager(this)!!
        listScreen.adapter = ListScreenAdapter(ArrayList(obP!!.screen)){ view: View, arrayList: ArrayList<ObjectScreen>, i: Int ->

        }
//            var anim = imageView4.drawable as Animatable
//            anim.start()

        Log.d("ddd",   realm.where(ObjectProject::class.java).equalTo("id",id).findFirst().toString())
        //
        objectScreens.add(ObjectButton())


        imageVoter.setOnClickListener{
            val set = ConstraintSet()

            set.clone(mainLayout)
            set.clear(fading_edge_layout.id, ConstraintSet.TOP)
            if (idVoter) {
                set.connect(fading_edge_layout.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)

                idVoter=false
            }else {
                set.connect(fading_edge_layout.id, ConstraintSet.TOP, cardBody.id, ConstraintSet.BOTTOM, 0)
                idVoter=true

            }
            TransitionManager.beginDelayedTransition(mainLayout)
            set.applyTo(mainLayout)
        }

        listButtons.layoutManager = LinearLayoutManager(this)!!
        listButtons.adapter = ButtonsAdapter(objectScreens){

        }
        buttonAdd.setOnClickListener{
            objectScreens.add(ObjectButton())
            (listButtons.adapter as ButtonsAdapter).notifyItemInserted(objectScreens.size)
        }

        val itemLeftHelper = ItemTouchHelper(simpleLeftCallback)
        itemLeftHelper.attachToRecyclerView(listButtons)
        val itemRightHelper = ItemTouchHelper(simpleRightCallback)
        itemRightHelper.attachToRecyclerView(listButtons)
    }

    private var simpleLeftCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val position = viewHolder.adapterPosition
            objectScreens.removeAt(position)
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.repeatCount === 0) {
            onBackPressed()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


    override fun onBackPressed() {
        startActivity(Intent(this, StartActivity::class.java))

    }
}
