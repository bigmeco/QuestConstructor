package com.bigmeco.questconstructor.screen.activity

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bigmeco.questconstructor.R
import com.bigmeco.questconstructor.data.*
import com.bigmeco.questconstructor.models.image.ImageResponseModel
import com.bigmeco.questconstructor.presenter.ActionScreenPresenter
import com.bigmeco.questconstructor.screen.adapter.ButtonsGameAdapter
import com.bigmeco.questconstructor.statements.ImageRespons
import com.bigmeco.questconstructor.statements.ScreenConstant
import com.bigmeco.questconstructor.views.ActionScreenView
import com.google.firebase.firestore.FirebaseFirestore
import io.realm.Realm
import io.realm.RealmList


class ActionScreenActivity : MvpAppCompatActivity(), ActionScreenView {

    @InjectPresenter
    lateinit var actionScreenPresenter: ActionScreenPresenter

    @ProvidePresenter
    fun provideactionScreenPresenter(): ActionScreenPresenter {
        return ActionScreenPresenter()
    }


    val fireStoreDataBase = FirebaseFirestore.getInstance()
    var project = Project()
    var id: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = intent.getStringExtra("idProject")

        if ((intent.getBooleanExtra("saveProject", false))) {
             val realm = Realm.getDefaultInstance()


            val obProject= realm.copyFromRealm(realm.where(MyProject::class.java).equalTo("id", id).findFirst()!!)

            project.body = obProject.body
            project.idStyle = obProject.idStyle
            project.genre = obProject.genre
            project.name = obProject.name
            project.saveScreen = obProject.saveScreen
            project.status = obProject.status
            project.time = obProject.time
            project.screen = ArrayList()
            for (screen in obProject.screen!!.iterator()) {

                val objectScreen = Screen()
                objectScreen.body = screen.body
                objectScreen.id = screen.id
                objectScreen.image = screen.image
                objectScreen.status = screen.status
                objectScreen.buttons = ArrayList<Button>()

                for (button in screen.buttons!!.iterator()) {
                    val objectButton = Button()
                    objectButton.id = button.id
                    objectButton.status = button.status
                    objectButton.text = button.text
                    objectScreen.buttons!!.add(objectButton)
                }
                project.screen!!.add(objectScreen)
                Log.e("LOAD_HISTORY_OF_REPORTS", project.screen.toString())
            }
            when (project.idStyle!!) {
                0 -> setContentView(R.layout.activity_action_screen_cyberpunk)
                1 -> setContentView(R.layout.activity_action_screen)
            }
            updateScreen()
        } else {
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
    }


    private fun updateScreen() {
        Log.e("LOAD_HISTORY_OF_REPORTS", project.screen!!.toArray().toString())
        Log.e("LOAD_HISTORY_OF_REPORTS",project.saveScreen.toString())
        val imageUsers = findViewById<ImageView>(R.id.imageUsers)
        val textBody = findViewById<TextView>(R.id.textBody)
        val cardView = findViewById<FrameLayout>(R.id.cardView3)
        val mainLayout = findViewById<ConstraintLayout>(R.id.mainLayout)
        val listButton = findViewById<RecyclerView>(R.id.listButton)
        Log.e("LOAD_HISTORY_OF_REPORTS", project.screen!![project.saveScreen].body+"  fffff")
        Log.e("LOAD_HISTORY_OF_REPORTS", "entrance " + project.screen!![project.saveScreen].buttons!!+"  gsdfgdf")
        Log.e("LOAD_HISTORY_OF_REPORTS", "entrance " + project.saveScreen)
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
            cardView.visibility = View.GONE
            TransitionManager.beginDelayedTransition(mainLayout)
            set.applyTo(mainLayout)

        }
        if (project.screen!![project.saveScreen].image == null) {
            set.clone(mainLayout)
            set.setVisibility(cardView.id, ConstraintSet.GONE)
            cardView.visibility = View.GONE
            TransitionManager.beginDelayedTransition(mainLayout)
            set.applyTo(mainLayout)
        }
        project.screen!![project.saveScreen].image?.let { it1 -> imageResponseModel.getImageResponse(it1, imageRespons) }
//        Log.i("LOAD_HISTORY_OF_REPORTS", "entrance " + (realm.where(MyProject::class.java).equalTo("id", id).findFirst() == null))
        Log.e("LOAD_HISTORY_OF_REPORTS", "entrance " + project.screen!![project.saveScreen].buttons!!+"  gsdfgdf")
        Log.e("LOAD_HISTORY_OF_REPORTS", "entrance " + project.saveScreen)

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
        actionScreenPresenter.saveHistory(id, project)


    }
}
