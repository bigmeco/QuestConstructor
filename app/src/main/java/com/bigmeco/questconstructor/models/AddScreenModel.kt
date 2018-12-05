package com.bigmeco.questconstructor.models

import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.data.ObjectScreen
import com.bigmeco.questconstructor.interfaceModels.IAddScreenModel
import io.realm.Realm

class AddScreenModel:IAddScreenModel {
    private val realm = Realm.getDefaultInstance()

    override fun addScreen(objectProject: ObjectProject): Int? {
        val addScreen = ObjectScreen()
        addScreen.id = objectProject.screen?.size
        realm.executeTransaction {
            objectProject.screen!!.add(addScreen)
        }
        return addScreen.id
    }

}