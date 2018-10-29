package com.bigmeco.questconstructor

import android.content.Intent
import android.graphics.drawable.Animatable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.constraint.ConstraintSet
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionManager
import android.util.Log
import android.view.Window
import kotlinx.android.synthetic.main.activity_info_quest.*

class InfoQuestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_info_quest)
        imageStarInfo.setImageResource(R.drawable.anim_star25)
        val drawable = imageStarInfo.drawable
        if (drawable is Animatable) (drawable as Animatable).start()
        buttonGame.setOnClickListener{
            Log.d("startTest","nutton")

            val set = ConstraintSet()
            set.clone(mainLayout)
           // set.setVisibility(buttonGame.id,ConstraintSet.GONE)
            set.clear(buttonGame.id, ConstraintSet.END)
            set.setElevation(buttonGame.id, 20f)

            set.connect(buttonGame.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)
            val mySwapTransition = ChangeBounds()

            mySwapTransition.addListener(object : Transition.TransitionListener {
                override fun onTransitionStart(transition: Transition) {

                }
                override fun onTransitionEnd(transition: Transition) {
                    val intent = Intent(this@InfoQuestActivity, ActionScreenActivity::class.java)
                    val editor = PreferenceManager.getDefaultSharedPreferences(this@InfoQuestActivity).edit()
                    editor.putInt("idProject", 5)
                    editor.apply()
                    Log.d("ddd",5.toString())
                    startActivity(intent)
                    Log.d("startTest","start")

                }

                override fun onTransitionCancel(transition: Transition) {}
                override fun onTransitionPause(transition: Transition) {}
                override fun onTransitionResume(transition: Transition) {}
            })
            TransitionManager.go(Scene(mainLayout), mySwapTransition)
            set.applyTo(mainLayout)
        }
        buttonComment.setOnClickListener{
            val set = ConstraintSet()
            set.clone(mainLayout)
           // set.setVisibility(buttonGame.id,ConstraintSet.GONE)
            set.clear(buttonComment.id, ConstraintSet.START)
            set.setElevation(buttonComment.id, 20f)
            set.connect(buttonComment.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0)
            val mySwapTransition = ChangeBounds()

            mySwapTransition.addListener(object : Transition.TransitionListener {
                override fun onTransitionStart(transition: Transition) {

                }
                override fun onTransitionEnd(transition: Transition) {
                    val intent = Intent(this@InfoQuestActivity, StartActivity::class.java)
//            val editor = PreferenceManager.getDefaultSharedPreferences(this).edit()
//            editor.putInt("idProject", objectProject.id!!)
//            editor.apply()
//            Log.d("ddd",objectProject.id.toString())
                    startActivity(intent)
                }

                override fun onTransitionCancel(transition: Transition) {}
                override fun onTransitionPause(transition: Transition) {}
                override fun onTransitionResume(transition: Transition) {}
            })
            TransitionManager.go(Scene(mainLayout), mySwapTransition)
            set.applyTo(mainLayout)
        }



    }
}
