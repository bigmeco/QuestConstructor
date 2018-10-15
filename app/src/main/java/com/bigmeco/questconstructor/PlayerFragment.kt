package com.bigmeco.questconstructor

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import kotlinx.android.synthetic.main.fragment_player.*


class PlayerFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView4.setOnClickListener{
            val animationX = ObjectAnimator.ofFloat(imageView4, "scaleX", 1F)
            val animationY = ObjectAnimator.ofFloat(imageView4, "scaleY", 1F)
            val set = AnimatorSet()
            set.play(animationX)
                    .with(animationY)
            set.interpolator = DecelerateInterpolator()
            set.start()
        }
    }
}
