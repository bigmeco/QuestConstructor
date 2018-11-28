package com.bigmeco.questconstructor.view.touches

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.FrameLayout

val SWIPE_MIN_DISTANCE_1 = 70
val SWIPE_THRESHOLD_VELOCITY_1 = 200

class EditListener (var mainLayout: ConstraintLayout, var fading_edge_layout: FrameLayout) : GestureDetector.SimpleOnGestureListener() {
    val set = ConstraintSet()

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        set.clone(mainLayout)
        set.clear(fading_edge_layout.id, ConstraintSet.TOP)

        if (e1.x - e2.x > SWIPE_MIN_DISTANCE_1 && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY_1) {
            return false // справа налево
        } else if (e2.x - e1.x > SWIPE_MIN_DISTANCE_1 && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY_1) {
            return false // слева направо
        }

        if (e1.y - e2.y > SWIPE_MIN_DISTANCE_1 && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY_1) {
            set.clear(fading_edge_layout.id,ConstraintSet.TOP)
            set.clear(fading_edge_layout.id,ConstraintSet.BOTTOM)
            set.connect(fading_edge_layout.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
            TransitionManager.beginDelayedTransition(mainLayout)
            set.applyTo(mainLayout)

            return false // снизу вверх
        } else if (e2.y - e1.y > SWIPE_MIN_DISTANCE_1 && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY_1) {
            return false // сверху вниз
        }
        return false
    }
}

