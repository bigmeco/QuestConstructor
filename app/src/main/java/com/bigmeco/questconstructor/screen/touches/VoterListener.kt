package com.bigmeco.questconstructor.screen.touches

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ScrollView

val SWIPE_MIN_DISTANCE = 70
val SWIPE_THRESHOLD_VELOCITY = 200

class VoterListener (var mainLayout: ConstraintLayout, var fading_edge_layout: ScrollView) : GestureDetector.SimpleOnGestureListener() {
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
            set.connect(fading_edge_layout.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 512)
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

