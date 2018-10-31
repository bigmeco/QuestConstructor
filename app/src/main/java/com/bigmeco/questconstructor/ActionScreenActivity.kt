package com.bigmeco.questconstructor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_action_screen.*
import kotlinx.android.synthetic.main.activity_action_screen_fantasy.*

class ActionScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //   getWindow().setBackgroundDrawable(null)
Log.d("startTest","ativiti")
        if (true) {
            Log.d("startTest","ifdo")

            setContentView(R.layout.activity_action_screen)
            Log.d("startTest","if")

        } else {
            setContentView(R.layout.activity_action_screen_cyberpunk)

        }
       var listButton = findViewById<RecyclerView>(R.id.listButton)
//
//        imageBackground.setImageResource(R.drawable.spellbook)
        listButton.layoutManager = LinearLayoutManager(this)

        var ibj = arrayListOf<ObjectButton>(ObjectButton())
        ibj.add(ObjectButton())
        ibj.add(ObjectButton())
        listButton.adapter = ButtonsGameAdapter(ibj) {
        }
    }

}
