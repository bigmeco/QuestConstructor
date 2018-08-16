package com.bigmeco.questconstructor

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v4.view.GravityCompat
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionManager
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_creator.*
import android.R.attr.visible
import android.view.View
import android.graphics.drawable.ColorDrawable
import android.R.attr.button
import android.animation.ArgbEvaluator
import com.bigmeco.questconstructor.R.id.textView
import com.bigmeco.questconstructor.R.id.textView
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener


class CreatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator)
        val set = ConstraintSet()

        imageBack.setOnClickListener{
            finish()
        }
        buttonBack.setOnClickListener{
            if ( textBack.text == getString(R.string.back)) {
                transitionName(set)
            } else{
                finish()

            }
        }
        buttonNext.setOnClickListener{

            transitionBody(set)

        }
    }


    private fun transitionName(set: ConstraintSet) {
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), resources.getColor(R.color.pleyColor), resources.getColor(R.color.createColor))
        colorAnimation.addUpdateListener { animator -> buttonBack.setCardBackgroundColor(animator.animatedValue as Int) }
        colorAnimation.addUpdateListener { animator -> buttonNext.setCardBackgroundColor(animator.animatedValue as Int) }
        colorAnimation.addUpdateListener { animator -> mainLayout.setBackgroundColor(animator.animatedValue as Int) }

        colorAnimation.duration = 600
        colorAnimation.start()
        set.clone(mainLayout)
        set.clear(cardBody.id, ConstraintSet.BOTTOM)
        set.setVisibility(textHelloy.id, ConstraintSet.INVISIBLE)
        set.setVisibility(textBody.id, ConstraintSet.INVISIBLE)
        imageLable.setImageResource(R.drawable.spellbook)
        textNext.text = getString(R.string.next)
        textBack.text = getString(R.string.exit)
        textHelloy.text = getString(R.string.greeting)
        textBody.text = getString(R.string.edit_text_name)



        val mySwapTransition = ChangeBounds()
        mySwapTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {}
            override fun onTransitionEnd(transition: Transition) {
                set.clone(mainLayout)
                set.setVisibility(textBody.id, ConstraintSet.VISIBLE)
                set.setVisibility(textHelloy.id, ConstraintSet.VISIBLE)
                 set.connect(cardName.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
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

    private fun transitionBody(set: ConstraintSet) {
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), resources.getColor(R.color.createColor), resources.getColor(R.color.pleyColor))
        colorAnimation.addUpdateListener { animator -> buttonBack.setCardBackgroundColor(animator.animatedValue as Int) }
        colorAnimation.addUpdateListener { animator -> buttonNext.setCardBackgroundColor(animator.animatedValue as Int) }
        colorAnimation.addUpdateListener { animator -> mainLayout.setBackgroundColor(animator.animatedValue as Int) }

        colorAnimation.duration = 600
        colorAnimation.start()
        set.clone(mainLayout)
        set.clear(cardName.id, ConstraintSet.BOTTOM)
        set.setVisibility(textHelloy.id, ConstraintSet.INVISIBLE)
        set.setVisibility(textBody.id, ConstraintSet.INVISIBLE)
        imageLable.setImageResource(R.drawable.castle)

        textNext.text = getString(R.string.done)
        textBack.text = getString(R.string.back)
        textHelloy.text = getString(R.string.continuation)
        textBody.text = getString(R.string.edit_text_body)



        val mySwapTransition = ChangeBounds()
        mySwapTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {}
            override fun onTransitionEnd(transition: Transition) {
                set.clone(mainLayout)
                set.setVisibility(textBody.id, ConstraintSet.VISIBLE)
                set.setVisibility(textHelloy.id, ConstraintSet.VISIBLE)
                 set.connect(cardBody.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
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
}
