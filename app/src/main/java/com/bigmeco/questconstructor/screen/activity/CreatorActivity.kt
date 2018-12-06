package com.bigmeco.questconstructor.screen.activity

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bigmeco.questconstructor.R
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.presenter.ProjectCreationPresenter
import com.bigmeco.questconstructor.views.ProjectCreationView
import kotlinx.android.synthetic.main.activity_creator.*




class CreatorActivity : MvpAppCompatActivity(), ProjectCreationView {

    @InjectPresenter
    lateinit var projectCreationPresenter: ProjectCreationPresenter

    @ProvidePresenter
    fun provideSplashPresenter(): ProjectCreationPresenter {
        return ProjectCreationPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator)
        val set = ConstraintSet()

        imageBack.setOnClickListener {
            finish()
        }

        buttonBack.setOnClickListener {
            if (textBack.text == getString(R.string.back)) {
                transitionName(set)
            } else {
                finish()

            }
        }

        buttonNext.setOnClickListener {
            if (textBack.text != getString(R.string.back)) {
                transitionBody(set)
            } else {
                val objectProject = ObjectProject()
                objectProject.name = editName.text.toString()
                objectProject.body = editBody.text.toString()
                objectProject.genre = spinnerGenres.selectedItem.toString()
                objectProject.time = spinnerTime.selectedItem.toString()
                projectCreationPresenter.createInitialDraft(objectProject)
            }
            transitionBody(set)
        }
    }

    override fun createInitialDraft(objectProject: ObjectProject) {
        val intent = Intent(this, CreatorScreenActivity::class.java)
        intent.putExtra("idProject", objectProject.id!!)
        startActivity(intent)
    }


    private fun transitionName(set: ConstraintSet) {
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), resources.getColor(R.color.pleyColor), resources.getColor(R.color.createColor))
        colorAnimation.addUpdateListener { animator -> buttonBack.setCardBackgroundColor(animator.animatedValue as Int) }
        colorAnimation.addUpdateListener { animator -> buttonNext.setCardBackgroundColor(animator.animatedValue as Int) }
        colorAnimation.addUpdateListener { animator -> mainLayout.setBackgroundColor(animator.animatedValue as Int) }

        colorAnimation.duration = 600
        colorAnimation.start()
        set.clone(mainLayout)
        set.clear(cardView.id, ConstraintSet.BOTTOM)
        set.setVisibility(textHelloy.id, ConstraintSet.INVISIBLE)
        set.setVisibility(editTextBody.id, ConstraintSet.INVISIBLE)
        imageLable.setImageResource(R.drawable.spellbook)
        textBack.text = getString(R.string.exit)
        textHelloy.text = getString(R.string.greeting)
        editTextBody.text = getString(R.string.edit_text_name)

        val mySwapTransition = ChangeBounds()
        mySwapTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {}
            override fun onTransitionEnd(transition: Transition) {
                set.clone(mainLayout)
                set.setVisibility(editTextBody.id, ConstraintSet.VISIBLE)
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
        set.setVisibility(editTextBody.id, ConstraintSet.INVISIBLE)
        imageLable.setImageResource(R.drawable.castle)

        textBack.text = getString(R.string.back)
        textHelloy.text = getString(R.string.continuation)
        editTextBody.text = getString(R.string.edit_text_body)


        val mySwapTransition = ChangeBounds()
        mySwapTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {}
            override fun onTransitionEnd(transition: Transition) {
                set.clone(mainLayout)
                set.setVisibility(editTextBody.id, ConstraintSet.VISIBLE)
                set.setVisibility(textHelloy.id, ConstraintSet.VISIBLE)
                set.connect(cardView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
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
