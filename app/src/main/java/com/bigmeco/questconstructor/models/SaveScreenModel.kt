package com.bigmeco.questconstructor.models

import com.bigmeco.questconstructor.data.ObjectButton
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.data.ObjectScreen
import com.bigmeco.questconstructor.interfaceModels.ISaveScreenModel
import io.realm.Realm

class SaveScreenModel : ISaveScreenModel {
    override fun fillSaveScreen(objectProjects: ObjectProject, objectScreen: ObjectScreen,
                                objectButtons: ArrayList<ObjectButton>, body: String,
                                idScreen: Int, idButton: Int?): ArrayList<ObjectScreen> {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {

            if (idButton != null) {
                objectButtons[idButton].id = idScreen
            }
            objectScreen.body = body
            objectScreen.status = objectScreen.thereIsNull()

            objectScreen.buttons!!.clear()
            objectScreen.buttons!!.addAll(objectButtons)


            for (i in 0 until objectScreen.buttons!!.size) {
                if (objectScreen.buttons!![i]!!.thereIsNull()) {
                    objectScreen.buttons!![i]!!.status = true
                } else {
                    objectScreen.buttons!![i]!!.status = false
                    break
                }
            }
            if (objectButtons.size != 0) {
                for (i in 0 until objectScreen.buttons!!.size) {
                    if (objectScreen.buttons!![i]!!.status!!) {
                        objectScreen.status = true
                    } else {
                        objectScreen.status = false
                        break
                    }
                }
            } else {
                objectScreen.status = false
            }
            if (objectProjects.thereIsNull()) {
                for (i in 0 until objectProjects.screen!!.size) {
                    if (objectProjects.screen!![i]!!.status!!) {
                        objectProjects.status = true
                    } else {
                        objectProjects.status = false
                        break
                    }
                }
            } else {
                objectProjects.status = false
            }
        }
        return ArrayList(objectProjects.screen)
    }
}