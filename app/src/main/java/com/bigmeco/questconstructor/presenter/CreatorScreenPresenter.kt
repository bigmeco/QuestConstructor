package com.bigmeco.questconstructor.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.models.AddScreenModel
import com.bigmeco.questconstructor.models.CreatorScreenModel
import com.bigmeco.questconstructor.views.CreatorScreenView

@InjectViewState
class CreatorScreenPresenter : MvpPresenter<CreatorScreenView>() {

    fun getProject(idProject: Int) {
        val creatorScreenModel = CreatorScreenModel()
        creatorScreenModel.getProject(idProject)?.let { viewState.getProject(it) }
    }

    fun addScreen(objectProject: ObjectProject) {
        val addScreenModel= AddScreenModel()
        addScreenModel.addScreen(objectProject)?.let { viewState.addScreen(it) }
    }
}