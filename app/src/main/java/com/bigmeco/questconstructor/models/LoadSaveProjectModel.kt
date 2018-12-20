package com.bigmeco.questconstructor.models

import android.util.Log
import com.bigmeco.questconstructor.data.Button
import com.bigmeco.questconstructor.data.MyProject
import com.bigmeco.questconstructor.data.Project
import com.bigmeco.questconstructor.data.Screen
import com.bigmeco.questconstructor.interfaceModels.ILoadSaveProjectModel
import io.realm.Realm

class LoadSaveProjectModel:ILoadSaveProjectModel {

    var project = Project()

    val realm = Realm.getDefaultInstance()

    override fun loadHistory(id: String, loadProject: (Project) -> Unit) {

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
        loadProject.invoke(project)
    }


}