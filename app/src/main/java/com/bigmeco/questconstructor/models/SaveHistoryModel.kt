package com.bigmeco.questconstructor.models

import com.bigmeco.questconstructor.data.MyButton
import com.bigmeco.questconstructor.data.MyProject
import com.bigmeco.questconstructor.data.MyScreen
import com.bigmeco.questconstructor.data.Project
import com.bigmeco.questconstructor.interfaceModels.ISaveHistoryModel
import com.bigmeco.questconstructor.statements.ScreenConstant
import io.realm.Realm
import io.realm.RealmList

class SaveHistoryModel: ISaveHistoryModel {
    val realm: Realm = Realm.getDefaultInstance()

  override  fun saveHistory(id: String, project: Project) {
      if(project.saveScreen== ScreenConstant.SCREEN_EXIT) {
          project.saveScreen=0
      }
          var objectProject = MyProject()
          if (realm.where(MyProject::class.java).equalTo("id", id).findFirst() == null) {
              objectProject.id = id
              objectProject.idStyle = project.idStyle
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
                  objectScreen.buttons = RealmList()
                  for (button in screen.buttons!!.iterator()) {
                      val objectButton = MyButton()
                      objectButton.id = button.id
                      objectButton.status = button.status
                      objectButton.text = button.text
                      objectScreen.buttons!!.add(objectButton)
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