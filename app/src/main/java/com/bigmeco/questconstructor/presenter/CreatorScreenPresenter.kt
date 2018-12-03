package com.bigmeco.questconstructor.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.bigmeco.questconstructor.QuestConstructorApplication
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.views.CreatorScreenView
import io.realm.Realm

@InjectViewState
class CreatorScreenPresenter : MvpPresenter<CreatorScreenView>() {
    private val realm = Realm.getDefaultInstance()
    private var objectProject: ObjectProject? = ObjectProject()

    fun getProject(idProject:Int) {
        Realm.init(QuestConstructorApplication().getInstance())



        objectProject = realm.where(ObjectProject::class.java).equalTo("id", idProject).findFirst()
        objectProject?.let { viewState.getProject(it) }


    }
}