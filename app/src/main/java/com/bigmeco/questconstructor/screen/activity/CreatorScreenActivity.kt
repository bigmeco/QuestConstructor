package com.bigmeco.questconstructor.screen.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_creator_screen.*
import android.content.Intent
import android.view.KeyEvent
import android.preference.PreferenceManager
import android.graphics.drawable.Animatable
import android.support.constraint.ConstraintSet
import android.support.v4.app.Fragment
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionManager
import io.realm.Realm
import android.view.GestureDetector
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bigmeco.questconstructor.data.ObjectButton
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.data.ObjectScreen
import com.bigmeco.questconstructor.R
import com.bigmeco.questconstructor.presenter.CreatorScreenPresenter
import com.bigmeco.questconstructor.screen.adapter.ListScreenAdapter
import com.bigmeco.questconstructor.screen.fragments.CreatorScreenFragment
import com.bigmeco.questconstructor.screen.touches.VoterListener
import com.bigmeco.questconstructor.views.CreatorScreenView


//class CreatorScreenActivity : AppCompatActivity() {
//
//    val realm = Realm.getDefaultInstance()
//    var idScreen = 0
//    var idButton: Int? = null
//    private var objectProject: ObjectProject? = ObjectProject()
//    var objectButton: ArrayList<ObjectButton> = ArrayList()
//    var id: Int? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_creator_screen)
//        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
//        id = preferences.getInt("idProject", 0)
//        val drawable = imageVoter.drawable
//
//        if (drawable is Animatable) {
//            (drawable as Animatable).start()
//        }
//
//
//        //
//        objectProject = realm.where(ObjectProject::class.java).equalTo("id", id).findFirst()
////        if (objectProject != null) {
////            for (i in 0..objectProject!!.screen!!.size)
////                objectProject!!.screen
////        }
//        objectButton = ArrayList(objectProject!!.screen!![idScreen]!!.buttons)
//
//
//        Log.d("ddd", realm.where(ObjectProject::class.java).equalTo("id", id).findFirst().toString())
//        //
//
//
//        val gdt = GestureDetector(GestureListener(mainLayout, fading_edge_layout, cardBody))
//        imageVoter.setOnTouchListener { view, event ->
//            gdt.onTouchEvent(event)
//            true
//        }
//        imageScreen.setOnClickListener {
//
//        }
//        imagePlusScreen.setOnClickListener {
//            var addScreen = ObjectScreen()
//            addScreen.id = objectProject!!.screen!!.size
//            realm.executeTransaction {
//                objectProject!!.screen!!.add(addScreen)
//            }
//            screenUpdate(idScreen)
//        }
//
//        listScreen.layoutManager = LinearLayoutManager(this)!!
//        listScreenUpdate(ArrayList(objectProject!!.screen))
//        listButtons.layoutManager = LinearLayoutManager(this)!!
//        listButtonUpdate(objectButton)
//        buttonAdd.setOnClickListener {
//            objectButton!!.add(ObjectButton())
//            (listButtons.adapter as ButtonsAdapter).notifyItemInserted(objectButton!!.size)
//        }
//
//        val itemLeftHelper = ItemTouchHelper(simpleLeftCallback)
//        itemLeftHelper.attachToRecyclerView(listButtons)
//        val itemRightHelper = ItemTouchHelper(simpleRightCallback)
//        itemRightHelper.attachToRecyclerView(listButtons)
//    }
//
//    private var simpleLeftCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//
//        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
//            return false
//        }
//
//        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
//            val position = viewHolder.adapterPosition
//            objectButton!!.removeAt(position)
//            listButtons.adapter!!.notifyDataSetChanged()
//        }
//    }
//    private var simpleRightCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
//
//        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
//            return false
//        }
//
//        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
//            listButtons.adapter!!.notifyDataSetChanged()
//
//        }
//    }
//
//    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
//        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
//                && keyCode == KeyEvent.KEYCODE_BACK
//                && event.repeatCount === 0) {
//            onBackPressed()
//            return true
//        }
//        return super.onKeyDown(keyCode, event)
//    }
//
//
//    override fun onBackPressed() {
//        startActivity(Intent(this, StartActivity::class.java))
//
//    }
//
//    private fun screenUpdate(idScreen: Int) {
//        realm.executeTransaction {
//            if (idButton != null) {
//                objectButton[idButton!!].id=idScreen
//                objectButton[idButton!!].status= objectButton[idButton!!].thereIsNull()
//            }
//            objectProject!!.screen!![this.idScreen]!!.image = editTextBody.text.toString()
//            objectProject!!.screen!![this.idScreen]!!.body = editTextBody.text.toString()
//            objectProject!!.screen!![this.idScreen]!!.body = editTextBody.text.toString()
//            objectProject!!.screen!![this.idScreen]!!.buttons!!.deleteAllFromRealm()
//            objectProject!!.screen!![this.idScreen]!!.buttons!!.addAll(objectButton)
//            for (i in 0 until objectProject!!.screen!![this.idScreen]!!.buttons!!.size) {
//                Log.d("testy", realm.where(ObjectProject::class.java).equalTo("id", id).findFirst()!!.screen!![this.idScreen]!!.buttons!![i].toString())
//                Log.d("testy", realm.where(ObjectProject::class.java).equalTo("id", id).findFirst()!!.screen!![this.idScreen]!!.buttons!![0].toString())
//
//                if (objectProject!!.screen!![this.idScreen]!!.buttons!![i]!!.status!!) {
//                    objectProject!!.screen!![this.idScreen]!!.status = true
//                } else {
//                    objectProject!!.screen!![this.idScreen]!!.status = false
//                    break
//                }
//            }
//            for (i in 0 until objectProject!!.screen!!.size) {
//                if (objectProject!!.screen!![i]!!.status!!) {
//                    objectProject!!.status = true
//                } else {
//                    objectProject!!.status = false
//                    break
//                }
//            }
//        }
//        Log.d("testy", realm.where(ObjectProject::class.java).equalTo("id", id).findFirst()!!.screen!![this.idScreen].toString())
//        Log.d("testy", realm.where(ObjectProject::class.java).equalTo("id", id).findFirst()!!.screen!![idScreen].toString())
//
//        this.idScreen = idScreen
//        idButton=null
//        objectButton = ArrayList(objectProject!!.screen!![idScreen]!!.buttons)
//
//        editTextBody.setText(objectProject!!.screen!![idScreen]!!.body)
//        listButtonUpdate(objectButton)
//        listButtons.invalidate()
//        listScreenUpdate(ArrayList(objectProject!!.screen))
//        listScreen.invalidate()
//    }
//
//    private fun listScreenUpdate(screens: ArrayList<ObjectScreen>) {
//        listScreen.adapter = ListScreenAdapter(screens) {
//            val set = ConstraintSet()
//
//            set.clone(mainLayout)
//            set.clear(fading_edge_layout.id, ConstraintSet.TOP)
//            set.connect(fading_edge_layout.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
//            val mySwapTransition = ChangeBounds()
//            mySwapTransition.addListener(object : Transition.TransitionListener {
//                override fun onTransitionStart(transition: Transition) {}
//                override fun onTransitionEnd(transition: Transition) {
//                    screenUpdate(it.id!!)
//                    set.clone(mainLayout)
//                    set.connect(fading_edge_layout.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
//                    TransitionManager.beginDelayedTransition(mainLayout)
//                    set.applyTo(mainLayout)
//                }
//
//                override fun onTransitionCancel(transition: Transition) {}
//                override fun onTransitionPause(transition: Transition) {}
//                override fun onTransitionResume(transition: Transition) {}
//            })
//
//            TransitionManager.go(Scene(mainLayout), mySwapTransition)
//            //TransitionManager.beginDelayedTransition(mainLayout)
//            set.applyTo(mainLayout)
//        }
//    }
//
//
//    private fun listButtonUpdate(buttons: ArrayList<ObjectButton>) {
//        listButtons.adapter = ButtonsAdapter(buttons) {
//            idButton = it
//            val set = ConstraintSet()
//            set.clone(mainLayout)
//            set.clear(fading_edge_layout.id, ConstraintSet.TOP)
//            set.connect(fading_edge_layout.id, ConstraintSet.TOP, cardBody.id, ConstraintSet.BOTTOM, 0)
//            TransitionManager.beginDelayedTransition(mainLayout)
//            set.applyTo(mainLayout)
//        }
//
//    }
//}
//
//


class CreatorScreenActivity :  MvpAppCompatActivity(), CreatorScreenView {
    private var idProject = 0

    private var idScreen = 0
    private var objectProject: ObjectProject? = ObjectProject()
    private var objectScreen: ObjectScreen? = ObjectScreen()
    private var oldFragment: Fragment? = null
    val realm = Realm.getDefaultInstance()

    @InjectPresenter
    lateinit var splashPresenter: CreatorScreenPresenter

    @ProvidePresenter
    fun provideSplashPresenter(): CreatorScreenPresenter {
        return CreatorScreenPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator_screen)
        Realm.init(this)

            val preferences = PreferenceManager.getDefaultSharedPreferences(this)
            idProject = preferences.getInt("idProject", 0)
            idScreen = preferences.getInt("idScreen", 0)

        splashPresenter.getProject(idProject)


        val drawable = imageVoter.drawable
        if (drawable is Animatable) (drawable as Animatable).start()


        val gdt = GestureDetector(VoterListener(mainLayout, fading_edge_layout))
        imageVoter.setOnTouchListener { _, event ->
            gdt.onTouchEvent(event)
            true
        }
        transitionFragment(CreatorScreenFragment(), idScreen)


        imagePlusScreen.setOnClickListener {
            screenUpdate {
                val addScreen = ObjectScreen()
                addScreen.id = objectProject?.screen?.size
                realm.executeTransaction {
                    objectProject!!.screen!!.add(addScreen)
                }
                val bundle = Bundle()
                bundle.putInt("IDscreen", addScreen.id!!)
                oldFragment?.arguments = bundle
                transitionFragment(CreatorScreenFragment(), addScreen.id!!)
                listScreenUpdate(ArrayList(objectProject?.screen))
                listScreen.invalidate()
                screenUpdate {

                }
            }

        }



    }

    private fun transitionFragment(newFragment: Fragment, idScreen: Int) {
        oldFragment= newFragment
        val bundle = Bundle()
        bundle.putInt("project", idProject)
        bundle.putInt("screen", idScreen)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmentScreen, newFragment)
        newFragment.arguments = bundle
        ft.commit()

    }


    private fun screenUpdate(listener: () -> Unit) {
        val set = ConstraintSet()
        set.clone(mainLayout)
        set.clear(fading_edge_layout.id, ConstraintSet.TOP)
        set.connect(fading_edge_layout.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
        val mySwapTransition = ChangeBounds()

        mySwapTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {}
            override fun onTransitionEnd(transition: Transition) {
                listener.invoke()
                objectScreen = objectProject?.screen?.get(idScreen)
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
        set.applyTo(mainLayout)

    }


     fun listScreenUpdate(screens: ArrayList<ObjectScreen>) {
        listScreen.adapter = ListScreenAdapter(screens) { objectScreen: ObjectScreen, i: Int ->
            screenUpdate {
                val bundle = Bundle()
                bundle.putInt("IDscreen", i)
                oldFragment?.arguments = bundle
                transitionFragment(CreatorScreenFragment(), i)

            }


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


    override fun getProject(objectProject: ObjectProject) {
        this.objectProject =objectProject
        objectScreen = objectProject.screen?.get(idScreen)
        listScreen.layoutManager = LinearLayoutManager(this)
        listScreenUpdate(ArrayList(objectProject.screen))
    }
}
