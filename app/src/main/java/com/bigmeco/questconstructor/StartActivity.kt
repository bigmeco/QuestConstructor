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

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val set = ConstraintSet()

        ItemUp.setOnClickListener{
            OpenLayout(set,linearLayout)
        }
        itemDown.setOnClickListener{
            OpenLayout(set,linearLayout)
        }

        ItemCreator.setOnClickListener {
            transitionCreator(CreatorFragment(),set,ItemCreator)
        }
        itemPlayer.setOnClickListener {

        }
    }

    private fun transitionCreator(newFragment: Fragment,set: ConstraintSet, layout: TextView) {
        set.clone(mainLayout)
            set.setVisibility(layout.id,ConstraintSet.GONE)
        TransitionManager.beginDelayedTransition(mainLayout)
        set.applyTo(mainLayout)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmentCreator, newFragment)
        ft.commit()
    }
    private fun OpenLayout(set: ConstraintSet, layout: LinearLayout) {
        set.clone(mainLayout)
        if (layout.visibility != View.GONE) {
            set.setVisibility(layout.id,ConstraintSet.GONE)
        } else {
            set.setVisibility(layout.id,ConstraintSet.VISIBLE)
        }
        TransitionManager.beginDelayedTransition(mainLayout)
        set.applyTo(mainLayout)
    }
}
