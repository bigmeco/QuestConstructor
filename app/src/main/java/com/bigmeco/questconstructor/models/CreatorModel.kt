package com.bigmeco.questconstructor.models

import android.app.Activity
import android.content.Context
import android.view.View
import com.bigmeco.questconstructor.QuestConstructorApplication
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.interfaceModels.ICreatorModel
import io.realm.Realm
import io.realm.internal.SyncObjectServerFacade

class CreatorModel : ICreatorModel {

    private val test = ArrayList<ObjectProject>()

    override fun getProjects(): ArrayList<ObjectProject>{
        val realm: Realm = Realm.getDefaultInstance()

        test.addAll(realm.where(ObjectProject::class.java).findAll())
        test.add(ObjectProject())
        return test
    }

}