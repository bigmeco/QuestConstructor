package com.bigmeco.questconstructor.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.bigmeco.questconstructor.data.Project
import com.bigmeco.questconstructor.interfaceModels.IDownloadProjectModel
import com.bigmeco.questconstructor.interfaceModels.ILoadSaveProjectModel
import com.bigmeco.questconstructor.interfaceModels.ISaveHistoryModel
import com.bigmeco.questconstructor.models.DownloadProjectModel
import com.bigmeco.questconstructor.models.LoadSaveProjectModel
import com.bigmeco.questconstructor.models.SaveHistoryModel
import com.bigmeco.questconstructor.views.ActionScreenView


@InjectViewState
class ActionScreenPresenter : MvpPresenter<ActionScreenView>() {

    fun saveHistory(id: String, project: Project) {
        val save: ISaveHistoryModel = SaveHistoryModel()
        save.saveHistory(id, project)
    }
    fun loadHistory(id: String, loadScreen: (Project) -> Unit) {
        val load: ILoadSaveProjectModel = LoadSaveProjectModel()
        load.loadHistory(id,loadScreen)
    }

    fun downloadProject(id: String, loadProject: (Project) -> Unit) {
        val download: IDownloadProjectModel = DownloadProjectModel()
        download.downloadProject(id,loadProject)
    }
}