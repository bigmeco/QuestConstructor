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
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.widget.CardView
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionManager
import android.util.Log
import io.realm.Realm
import android.view.MotionEvent
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.GestureDetector
import android.widget.ScrollView
import io.realm.RealmList


class CreatorScreenActivity : AppCompatActivity() {

    val realm = Realm.getDefaultInstance()
    var idScreen = 0
    var objectProject: ObjectProject? = ObjectProject()
    var objectButton: ArrayList<ObjectButton> = ArrayList()
    var id: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator_screen)
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        id = preferences.getInt("idProject", 0)
        val drawable = imageVoter.drawable

        if (drawable is Animatable) {
            (drawable as Animatable).start()
        }


        //
        objectProject = realm.where(ObjectProject::class.java).equalTo("id", id).findFirst()
        if (objectProject != null) {
            for (i in 0..objectProject!!.screen!!.size)
                objectProject!!.screen
        }
        objectButton = ArrayList(objectProject!!.screen!![idScreen]!!.buttons)


        Log.d("ddd", realm.where(ObjectProject::class.java).equalTo("id", id).findFirst().toString())
        //


        val gdt = GestureDetector(GestureListener(mainLayout, fading_edge_layout, cardBody))
        imageVoter.setOnTouchListener { view, event ->
            gdt.onTouchEvent(event)
            true
        }
        imageScreen.setOnClickListener {

        }

        listScreen.layoutManager = LinearLayoutManager(this)!!
        listScreenUpdate(ArrayList(objectProject!!.screen))
        listButtons.layoutManager = LinearLayoutManager(this)!!
        listButtonUpdate(objectButton)
        buttonAdd.setOnClickListener {
            objectButton!!.add(ObjectButton())
            (listButtons.adapter as ButtonsAdapter).notifyItemInserted(objectButton!!.size)
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
            objectButton!!.removeAt(position)
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

    private fun screenUpdate(idScreen: Int) {
        realm.executeTransaction {

            objectProject!!.screen!![this.idScreen]!!.image = editTextBody.text.toString()

            objectProject!!.screen!![this.idScreen]!!.body = editTextBody.text.toString()
            objectProject!!.screen!![this.idScreen]!!.body = editTextBody.text.toString()
            objectProject!!.screen!![this.idScreen]!!.buttons!!.deleteAllFromRealm()
            objectProject!!.screen!![this.idScreen]!!.buttons!!.addAll(objectButton)
            for (i in 0 until objectProject!!.screen!![this.idScreen]!!.buttons!!.size) {
                Log.d("testy", realm.where(ObjectProject::class.java).equalTo("id", id).findFirst()!!.screen!![this.idScreen]!!.buttons!![i].toString())

                if (objectProject!!.screen!![this.idScreen]!!.buttons!![i]!!.status!!) {
                    objectProject!!.screen!![this.idScreen]!!.status = true
                } else {
                    objectProject!!.screen!![this.idScreen]!!.status = false
                    break
                }
            }
            for (i in 0 until objectProject!!.screen!!.size) {
                if (objectProject!!.screen!![i]!!.status!!) {
                    objectProject!!.status = true
                } else {
                    objectProject!!.status = false
                    break
                }
            }
        }
        Log.d("testy", realm.where(ObjectProject::class.java).equalTo("id", id).findFirst()!!.screen!![this.idScreen].toString())
        this.idScreen = idScreen
        editTextBody.setText(objectProject!!.screen!![idScreen]!!.body)
        listButtonUpdate(objectButton)
        listButtons.invalidate()
        listScreenUpdate(ArrayList(objectProject!!.screen))
        listScreen.invalidate()
    }

    private fun listScreenUpdate(screens: ArrayList<ObjectScreen>) {
        listScreen.adapter = ListScreenAdapter(screens) {
            val set = ConstraintSet()

            set.clone(mainLayout)
            set.clear(fading_edge_layout.id, ConstraintSet.TOP)
            set.connect(fading_edge_layout.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
            val mySwapTransition = ChangeBounds()
            mySwapTransition.addListener(object : Transition.TransitionListener {
                override fun onTransitionStart(transition: Transition) {}
                override fun onTransitionEnd(transition: Transition) {
                    screenUpdate(it.id!!)
                    set.clone(mainLayout)
                    set.connect(fading_edge_layout.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
                    TransitionManager.beginDelayedTransition(mainLayout)
                    set.applyTo(mainLayout)
                }

                override fun onTransitionCancel(transition: Transition) {}
                override fun onTransitionPause(transition: Transition) {}
                override fun onTransitionResume(transition: Transition) {}
            })

            TransitionManager.go(Scene(mainLayout), mySwapTransition)
            //TransitionManager.beginDelayedTransition(mainLayout)
            set.applyTo(mainLayout)
        }
    }


    private fun listButtonUpdate(buttons: ArrayList<ObjectButton>) {
        listButtons.adapter = ButtonsAdapter(buttons) {
            objectProject!!.screen!![this.idScreen]!!.buttons!!
        }

    }
}


val SWIPE_MIN_DISTANCE = 70
val SWIPE_THRESHOLD_VELOCITY = 200

class GestureListener(var mainLayout: ConstraintLayout, var fading_edge_layout: ScrollView, var cardBody: CardView) : SimpleOnGestureListener() {
    val set = ConstraintSet()

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        set.clone(mainLayout)
        set.clear(fading_edge_layout.id, ConstraintSet.TOP)

        if (e1.x - e2.x > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            return false // справа налево
        } else if (e2.x - e1.x > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            return false // слева направо
        }

        if (e1.y - e2.y > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
            set.connect(fading_edge_layout.id, ConstraintSet.TOP, cardBody.id, ConstraintSet.BOTTOM, 0)
            TransitionManager.beginDelayedTransition(mainLayout)
            set.applyTo(mainLayout)


            return false // снизу вверх
        } else if (e2.y - e1.y > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
            set.connect(fading_edge_layout.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
            TransitionManager.beginDelayedTransition(mainLayout)
            set.applyTo(mainLayout)
            return false // сверху вниз
        }
        return false
    }
}