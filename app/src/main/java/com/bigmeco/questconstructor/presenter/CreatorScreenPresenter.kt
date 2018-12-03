package com.bigmeco.questconstructor.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.data.ObjectScreen
import com.bigmeco.questconstructor.views.CreatorScreenView
import io.realm.Realm
import io.realm.internal.SyncObjectServerFacade

@InjectViewState
class CreatorScreenPresenter : MvpPresenter<CreatorScreenView>() {
    private val realm = Realm.getDefaultInstance()
    private var objectProject: ObjectProject? = ObjectProject()

    fun getProject(idProject:Int) {

        objectProject = realm.where(ObjectProject::class.java).equalTo("id", idProject).findFirst()
        objectProject?.let { viewState.getProject(it) }


    }

    fun addScreen() {
        val addScreen = ObjectScreen()
        addScreen.id = objectProject?.screen?.size
        realm.executeTransaction {
            objectProject!!.screen!!.add(addScreen)
        }
        addScreen.id?.let { viewState.addScreen(it) }
    }
}