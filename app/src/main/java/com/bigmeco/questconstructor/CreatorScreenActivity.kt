package com.bigmeco.questconstructor

import android.app.Notification
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
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import io.realm.Realm
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.view.GestureDetector.SimpleOnGestureListener
import android.text.method.Touch.onTouchEvent
import com.bigmeco.questconstructor.R.id.imageView
import android.view.GestureDetector
import android.widget.ScrollView


class CreatorScreenActivity : AppCompatActivity() {

    val objectScreens = ArrayList<ObjectButton>()
    val realm = Realm.getDefaultInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator_screen)
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        var id = preferences.getInt("idProject", 0)
        val drawable = imageVoter.drawable

        if (drawable is Animatable) {
            (drawable as Animatable).start()
        }







        //
        var obP = realm.where(ObjectProject::class.java).equalTo("id", id).findFirst()
        if (obP != null) {
            for (i in 0..obP.screen!!.size)
                obP.screen
        }
        listScreen.layoutManager = LinearLayoutManager(this)!!
        listScreen.adapter = ListScreenAdapter(ArrayList(obP!!.screen)) { view: View, arrayList: ArrayList<ObjectScreen>, i: Int ->

        }

        Log.d("ddd", realm.where(ObjectProject::class.java).equalTo("id", id).findFirst().toString())
        //



        objectScreens.add(ObjectButton())

       val gdt = GestureDetector(GestureListener(mainLayout, fading_edge_layout, cardBody))
        imageVoter.setOnTouchListener { view, event ->
            gdt.onTouchEvent(event)
            true
        }

        listButtons.layoutManager = LinearLayoutManager(this)!!
        listButtons.adapter = ButtonsAdapter(objectScreens) {

        }
        buttonAdd.setOnClickListener {
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