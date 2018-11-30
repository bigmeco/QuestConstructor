package com.bigmeco.questconstructor.models

import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.data.ObjectScreen
import com.bigmeco.questconstructor.interfaceModels.IProjectCreationModel
import io.realm.Realm
import io.realm.RealmList


class ProjectCreationModel : IProjectCreationModel {

    val realm: Realm = Realm.getDefaultInstance()

    override fun getStandardProject(objectProject: ObjectProject): ObjectProject {
        objectProject.id = realm.where(ObjectProject::class.java).findAll().size
        objectProject.status = false
        val startProject = ObjectScreen()
        ObjectScreen().id = 0
        val objectScreen = RealmList<ObjectScreen>()
        objectScreen.add(startProject)
        objectProject.screen = objectScreen
        realm.beginTransaction()
        realm.insert(objectProject)
        realm.commitTransaction()
        return objectProject
    }
}