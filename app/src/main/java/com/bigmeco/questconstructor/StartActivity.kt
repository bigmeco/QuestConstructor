package com.bigmeco.questconstructor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import android.view.View
import android.widget.LinearLayout
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
