package com.bigmeco.questconstructor.models

import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.interfaceModels.ICreatorScreenModel
import io.realm.Realm

class CreatorScreenModel:ICreatorScreenModel {
    private val realm = Realm.getDefaultInstance()
    private var objectProject: ObjectProject? = ObjectProject()

    override fun getProject(idProject: Int): ObjectProject? {
        objectProject = realm.where(ObjectProject::class.java).equalTo("id", idProject).findFirst()
        return objectProject
    }

}