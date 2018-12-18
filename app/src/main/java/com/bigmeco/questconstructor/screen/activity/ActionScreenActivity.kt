package com.bigmeco.questconstructor.screen.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.TextureView
import android.view.View
import android.widget.TextView
import com.bigmeco.questconstructor.R
import com.bigmeco.questconstructor.data.*
import com.bigmeco.questconstructor.screen.adapter.ButtonsGameAdapter
import com.google.firebase.firestore.FirebaseFirestore
import io.realm.Realm
import io.realm.RealmList
import kotlinx.android.synthetic.main.*

import java.util.ArrayList

class ActionScreenActivity : AppCompatActivity() {
    val realm: Realm = Realm.getDefaultInstance()
    val fireStoreDataBase = FirebaseFirestore.getInstance()
    var project = Project()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //   getWindow().setBackgroundDrawable(null)

        intent.getIntExtra("idProject", 0)



        fireStoreDataBase.collection("questBody").document(intent.getStringExtra("idProject"))
                .get().addOnCompleteListener {
                    val document = it.result
                    Log.i("LOAD_HISTORY_OF_REPORTS", "entrance " + document!!.toObject(Project::class.java)!!.status)
                    project = document!!.toObject(Project::class.java)!!
                    when (project!!.idStyle) {
                        0 -> setContentView(R.layout.activity_action_screen_cyberpunk)
                        1 -> setContentView(R.layout.activity_action_screen)
                    }
                    Log.i("LOAD_HISTORY_OF_REPORTS", "entrance " + realm.where(MyProject::class.java).equalTo("id",project.id).isValid)
                    Log.i("LOAD_HISTORY_OF_REPORTS", "entrance " + realm.where(MyProject::class.java).equalTo("id",project.id).findFirst())
                    var listButton = findViewById<RecyclerView>(R.id.listButton)

                    listButton.layoutManager = LinearLayoutManager(this)

                    listButton.adapter = ButtonsGameAdapter(project.idStyle!!, project.screen!![project.saveScreen].buttons!!) {

                    }
                }

    }

    override fun onStop() {
        super.onStop()
        var objectProject = MyProject()

        if (!realm.where(MyProject::class.java).equalTo("id",project.id).isValid){

        objectProject.id = project.id
        objectProject.idStyle = project.idStyle
        objectProject.body = project.body
        objectProject.genre = project.genre
        objectProject.name = project.name
        objectProject.saveScreen = project.saveScreen
        objectProject.status = project.status
        objectProject.time = project.time
        objectProject.screen = RealmList()
        for (screen in project.screen!!.iterator()) {
            val objectScreen =  MyScreen()
            objectScreen.body = screen.body
            objectScreen.id = screen.id
            objectScreen.image = screen.image
            objectScreen.status = screen.status
            for (button in screen.buttons!!.iterator()) {
                val objectButton =  MyButton()
                objectButton.id = button.id
                objectButton.status= button.status
                objectButton.text= button.text
                objectScreen.buttons?.add(objectButton)
            }
            objectProject.screen!!.add(objectScreen)
        }
            realm.beginTransaction()
            realm.insert(objectProject)
            realm.commitTransaction()
        } else{
            realm.beginTransaction()
            objectProject = realm.where(MyProject::class.java).equalTo("id",project.id).findFirst()!!
            objectProject.saveScreen =project.saveScreen
            realm.commitTransaction()

        }

    }
}
