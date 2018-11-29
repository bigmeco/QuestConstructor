package com.bigmeco.questconstructor.screen.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.bigmeco.questconstructor.data.ObjectButton
import com.bigmeco.questconstructor.R
import com.bigmeco.questconstructor.screen.adapter.ButtonsGameAdapter

class ActionScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //   getWindow().setBackgroundDrawable(null)
Log.d("startTest","ativiti")
        if (false) {
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
