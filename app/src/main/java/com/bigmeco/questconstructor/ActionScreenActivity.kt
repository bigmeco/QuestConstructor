package com.bigmeco.questconstructor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_action_screen.*
import kotlinx.android.synthetic.main.activity_action_screen_fantasy.*

class ActionScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (true) {
            setContentView(R.layout.activity_action_screen)
        } else {
            setContentView(R.layout.activity_action_screen_fantasy)

        }
//       var imageBackground = findViewById<ImageView>(R.id.imageBackground)
//
//        imageBackground.setImageResource(R.drawable.spellbook)
        }
}
