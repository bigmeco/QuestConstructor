package com.bigmeco.questconstructor

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_start.*
import android.transition.Scene
import android.transition.Transition
import android.transition.ChangeBounds


class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val set = ConstraintSet()

        ItemUp.setOnClickListener {
            if (itemCreator.visibility == View.VISIBLE) {
                OpenLayout(set, linearLayout)
            } else {
                set.clone(mainLayout)
                set.clear(divider2.id, ConstraintSet.BOTTOM)
                set.connect(divider2.id, ConstraintSet.BOTTOM, itemPlayer.id, ConstraintSet.TOP, 0)
                set.setVisibility(itemCreator.id, ConstraintSet.VISIBLE)
                TransitionManager.beginDelayedTransition(mainLayout)
                set.applyTo(mainLayout)
            }
        }
        itemDown.setOnClickListener {
            if (itemPlayer.visibility == View.VISIBLE) {
                OpenLayout(set, linearLayout)
            } else {
                set.clone(mainLayout)
                set.clear(divider.id, ConstraintSet.TOP)
                set.connect(divider.id, ConstraintSet.TOP, itemCreator.id, ConstraintSet.BOTTOM, 0)
                set.setVisibility(itemPlayer.id, ConstraintSet.VISIBLE)
                TransitionManager.beginDelayedTransition(mainLayout)
                set.applyTo(mainLayout)
            }
        }

        itemCreator.setOnClickListener {
            transitionCreator(CreatorFragment(), set)
        }
        itemPlayer.setOnClickListener {
            transitionPlayer(CreatorFragment(), set)

        }

    }

    private fun transitionCreator(newFragment: Fragment, set: ConstraintSet) {
        set.clone(mainLayout)
        set.clear(divider2.id, ConstraintSet.BOTTOM)
        set.connect(divider2.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
        set.setVisibility(linearLayout.id, ConstraintSet.GONE)
        val mySwapTransition = ChangeBounds()
        mySwapTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {}
            override fun onTransitionEnd(transition: Transition) {
                set.clone(mainLayout)
                set.setVisibility(itemCreator.id, ConstraintSet.INVISIBLE)
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
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmentCreator, newFragment)
        ft.commit()

    }

    private fun transitionPlayer(newFragment: Fragment, set: ConstraintSet) {
        set.clone(mainLayout)
        set.clear(divider.id, ConstraintSet.TOP)
        set.connect(divider.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
        set.setVisibility(linearLayout.id, ConstraintSet.GONE)
        val mySwapTransition = ChangeBounds()
        mySwapTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {}
            override fun onTransitionEnd(transition: Transition) {
                set.clone(mainLayout)
                set.setVisibility(itemPlayer.id, ConstraintSet.INVISIBLE)
                TransitionManager.beginDelayedTransition(mainLayout)
                set.applyTo(mainLayout)
            }

            override fun onTransitionCancel(transition: Transition) {}
            override fun onTransitionPause(transition: Transition) {}
            override fun onTransitionResume(transition: Transition) {}
        })

        TransitionManager.go(Scene(mainLayout), mySwapTransition)
        set.applyTo(mainLayout)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmentPlayer, newFragment)
        ft.commit()
    }

    private fun OpenLayout(set: ConstraintSet, layout: LinearLayout) {
        set.clone(mainLayout)
        if (layout.visibility != View.GONE) {
            set.setVisibility(layout.id, ConstraintSet.GONE)
        } else {
            set.setVisibility(layout.id, ConstraintSet.VISIBLE)
        }
        TransitionManager.beginDelayedTransition(mainLayout)
        set.applyTo(mainLayout)
    }
}
