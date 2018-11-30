package com.bigmeco.questconstructor.models

import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.interfaceModels.ICreatorModel
import io.realm.Realm

class CreatorModel : ICreatorModel {

    val realm: Realm = Realm.getDefaultInstance()
    private val test = ArrayList<ObjectProject>()

    override fun getProjects(): ArrayList<ObjectProject>{
        test.addAll(realm.where(ObjectProject::class.java).findAll())
        test.add(ObjectProject())
        return test
    }

}