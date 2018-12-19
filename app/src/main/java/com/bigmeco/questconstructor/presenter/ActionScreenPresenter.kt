package com.bigmeco.questconstructor.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.bigmeco.questconstructor.data.Project
import com.bigmeco.questconstructor.interfaceModels.ISaveHistoryModel
import com.bigmeco.questconstructor.models.SaveHistoryModel
import com.bigmeco.questconstructor.views.ActionScreenView


@InjectViewState
class ActionScreenPresenter : MvpPresenter<ActionScreenView>() {

    fun saveHistory(id: String, project: Project) {
val save: ISaveHistoryModel = SaveHistoryModel()
save.saveHistory(id,project)
    }
}