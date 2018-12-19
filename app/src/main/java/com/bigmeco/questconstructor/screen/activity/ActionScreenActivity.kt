package com.bigmeco.questconstructor.screen.activity

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bigmeco.questconstructor.R
import com.bigmeco.questconstructor.data.MyButton
import com.bigmeco.questconstructor.data.MyProject
import com.bigmeco.questconstructor.data.MyScreen
import com.bigmeco.questconstructor.data.Project
import com.bigmeco.questconstructor.models.image.ImageResponseModel
import com.bigmeco.questconstructor.screen.adapter.ButtonsGameAdapter
import com.bigmeco.questconstructor.statements.ImageRespons
import com.bigmeco.questconstructor.statements.ScreenConstant
import com.google.firebase.firestore.FirebaseFirestore
import io.realm.Realm
import io.realm.RealmList


class ActionScreenActivity : AppCompatActivity() {
    val realm: Realm = Realm.getDefaultInstance()
    val fireStoreDataBase = FirebaseFirestore.getInstance()
    var project = Project()
    var id: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //   getWindow().setBackgroundDrawable(null)

        id = intent.getStringExtra("idProject")


        fireStoreDataBase.collection("questBody").document(id)
                .get().addOnCompleteListener { it ->
                    val document = it.result
                    project = document!!.toObject(Project::class.java)!!
                    when (project!!.idStyle) {
                        0 -> setContentView(R.layout.activity_action_screen_cyberpunk)
                        1 -> setContentView(R.layout.activity_action_screen)
                    }
                    updateScreen()

                }

    }


    private fun updateScreen() {
        val imageUsers = findViewById<ImageView>(R.id.imageUsers)
        val textBody = findViewById<TextView>(R.id.textBody)
        val cardView = findViewById<FrameLayout>(R.id.cardView3)
        val mainLayout = findViewById<ConstraintLayout>(R.id.mainLayout)
        val listButton = findViewById<RecyclerView>(R.id.listButton)
        textBody.text = project.screen!![project.saveScreen].body
        val imageResponseModel = ImageResponseModel()
        val imageRespons = ImageRespons()
        val set = ConstraintSet()
        imageRespons.respons = {
            set.clone(mainLayout)
            set.setVisibility(cardView.id, ConstraintSet.VISIBLE)
            TransitionManager.beginDelayedTransition(mainLayout)
            set.applyTo(mainLayout)
            imageUsers.setImageBitmap(it)
        }
        imageRespons.errorImage = {
            set.clone(mainLayout)
            set.setVisibility(cardView.id, ConstraintSet.GONE)
            cardView.visibility =View.GONE
            TransitionManager.beginDelayedTransition(mainLayout)
            set.applyTo(mainLayout)

        }
        if ( project.screen!![project.saveScreen].image==null){
            set.clone(mainLayout)
            set.setVisibility(cardView.id, ConstraintSet.GONE)
            cardView.visibility =View.GONE
            TransitionManager.beginDelayedTransition(mainLayout)
            set.applyTo(mainLayout)
        }
        project.screen!![project.saveScreen].image?.let { it1 -> imageResponseModel.getImageResponse(it1, imageRespons) }
        Log.i("LOAD_HISTORY_OF_REPORTS", "entrance " + (realm.where(MyProject::class.java).equalTo("id", id).findFirst() == null))
        Log.i("LOAD_HISTORY_OF_REPORTS", "entrance " + realm.where(MyProject::class.java).equalTo("id", id).findFirst())

        listButton.layoutManager = LinearLayoutManager(this)

        listButton.adapter = ButtonsGameAdapter(project.idStyle!!, project.screen!![project.saveScreen].buttons!!) { button ->
            project.saveScreen = button.id!!
            if (project.saveScreen != ScreenConstant.SCREEN_EXIT) {
                updateScreen()
            } else {
                onClick()
            }
        }
    }

    fun onClick() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Конец")
                .setCancelable(false)
                .setNegativeButton("ОК") { dialog, id -> startActivity(Intent(this, StartActivity::class.java)) }
        val alert = builder.create()
        alert.show()
    }

    override fun onStop() {
        super.onStop()
        var objectProject = MyProject()

        if (realm.where(MyProject::class.java).equalTo("id", id).findFirst() == null) {
            objectProject.id = id
            objectProject.body = project.body
            objectProject.genre = project.genre
            objectProject.name = project.name
            objectProject.saveScreen = project.saveScreen
            objectProject.status = project.status
            objectProject.time = project.time
            objectProject.screen = RealmList()
            for (screen in project.screen!!.iterator()) {
                val objectScreen = MyScreen()
                objectScreen.body = screen.body
                objectScreen.id = screen.id
                objectScreen.image = screen.image
                objectScreen.status = screen.status
                for (button in screen.buttons!!.iterator()) {
                    val objectButton = MyButton()
                    objectButton.id = button.id
                    objectButton.status = button.status
                    objectButton.text = button.text
                    objectScreen.buttons?.add(objectButton)
                }
                objectProject.screen!!.add(objectScreen)
            }
            realm.beginTransaction()
            realm.insert(objectProject)
            realm.commitTransaction()
        } else {
            realm.beginTransaction()
            objectProject = realm.where(MyProject::class.java).equalTo("id", id).findFirst()!!
            objectProject.saveScreen = project.saveScreen
            realm.commitTransaction()

        }

    }
}
