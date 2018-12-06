package com.bigmeco.questconstructor.screen.activity

import android.content.Intent
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionManager
import android.view.GestureDetector
import android.view.KeyEvent
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bigmeco.questconstructor.R
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.data.ObjectScreen
import com.bigmeco.questconstructor.presenter.CreatorScreenPresenter
import com.bigmeco.questconstructor.screen.adapter.ListScreenAdapter
import com.bigmeco.questconstructor.screen.fragments.CreatorScreenFragment
import com.bigmeco.questconstructor.screen.touches.VoterListener
import com.bigmeco.questconstructor.views.CreatorScreenView
import kotlinx.android.synthetic.main.activity_creator_screen.*


class CreatorScreenActivity : MvpAppCompatActivity(), CreatorScreenView {

    private var idProject = 0
    private var idScreen = 0
    private var objectProject: ObjectProject? = ObjectProject()
    private var objectScreen: ObjectScreen? = ObjectScreen()
    private var oldFragment: Fragment? = null

    @InjectPresenter
    lateinit var creatorScreenPresenter: CreatorScreenPresenter

    @ProvidePresenter
    fun provideSplashPresenter(): CreatorScreenPresenter {
        return CreatorScreenPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator_screen)
        idProject = intent.getIntExtra("idProject", 0)
        idScreen = intent.getIntExtra("idScreen", 0)
        creatorScreenPresenter.getProject(idProject)

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
                objectProject?.let { it1 -> creatorScreenPresenter.addScreen(it1) }
            }
        }
    }

    private fun transitionFragment(newFragment: Fragment, idScreen: Int) {
        oldFragment = newFragment
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
                nextScreen(i)
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

    private fun nextScreen(idNewScreen: Int) {
        val bundle = Bundle()
        bundle.putInt("IDscreen", idNewScreen)
        oldFragment?.arguments = bundle
        transitionFragment(CreatorScreenFragment(), idNewScreen)
    }

    override fun addScreen(idNewScreen: Int) {
        nextScreen(idNewScreen)
        listScreenUpdate(ArrayList(objectProject?.screen))
        listScreen.invalidate()
    }

    override fun getProject(objectProject: ObjectProject) {
        this.objectProject = objectProject
        objectScreen = objectProject.screen?.get(idScreen)
        listScreen.layoutManager = LinearLayoutManager(this)
        listScreenUpdate(ArrayList(objectProject.screen))
    }
}
