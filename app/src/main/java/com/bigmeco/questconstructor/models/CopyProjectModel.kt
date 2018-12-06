package com.bigmeco.questconstructor.models

import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.interfaceModels.ICopyProjectModel
import io.realm.Realm

class CopyProjectModel:ICopyProjectModel {
    private val realm = Realm.getDefaultInstance()

    override fun getCopyProject(idProject: Int): ObjectProject {
       return realm.copyFromRealm(realm.where(ObjectProject::class.java).equalTo("id", idProject).findAll()[0])!!
    }

}