package com.bigmeco.questconstructor

import android.graphics.drawable.Animatable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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

    }
}
