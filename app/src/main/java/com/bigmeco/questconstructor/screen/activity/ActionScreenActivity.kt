package com.bigmeco.questconstructor.screen.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.bigmeco.questconstructor.data.ObjectButton
import com.bigmeco.questconstructor.R
import com.bigmeco.questconstructor.data.InfoProject
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.data.Project
import com.bigmeco.questconstructor.screen.adapter.ButtonsGameAdapter
import com.google.firebase.firestore.FirebaseFirestore

class ActionScreenActivity : AppCompatActivity() {
    val fireStoreDataBase = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //   getWindow().setBackgroundDrawable(null)

       intent.getIntExtra("idProject", 0)

            Log.i("LOAD_HISTORY_OF_REPORTS", "entrance " + intent.getStringExtra("idProject"))

            fireStoreDataBase.collection("questBody").document(intent.getStringExtra("idProject"))
                    .get().addOnCompleteListener {
                        val document = it.result
                        Log.i("LOAD_HISTORY_OF_REPORTS", "entrance " + document!!.toObject(Project::class.java)!!.status)
                        val project = document!!.toObject(Project::class.java)
                        when(project!!.idStyle) {
                            0 -> setContentView(R.layout.activity_action_screen)
                            1 -> setContentView(R.layout.activity_action_screen_cyberpunk)
                        }


                        var listButton = findViewById<RecyclerView>(R.id.listButton)
//
//        imageBackground.setImageResource(R.drawable.spellbook)
                        listButton.layoutManager = LinearLayoutManager(this)

                        var ibj = arrayListOf<ObjectButton>(ObjectButton())
                        ibj.add(ObjectButton())
                        ibj.add(ObjectButton())
                        listButton.adapter = ButtonsGameAdapter(project.idStyle!!,ibj) {
                        }
                    }

    }

}
