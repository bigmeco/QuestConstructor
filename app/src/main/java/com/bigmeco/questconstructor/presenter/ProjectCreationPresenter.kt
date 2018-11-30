package com.bigmeco.questconstructor.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.models.ProjectCreationModel
import com.bigmeco.questconstructor.views.ProjectCreationView

@InjectViewState
class ProjectCreationPresenter : MvpPresenter<ProjectCreationView>() {

    fun createInitialDraft(objectProject: ObjectProject) {
        val projectCreationModel= ProjectCreationModel()
        viewState.createInitialDraft(projectCreationModel.getStandardProject(objectProject))
    }
}
