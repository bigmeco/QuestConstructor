package com.bigmeco.questconstructor.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.bigmeco.questconstructor.models.CopyProjectModel
import com.bigmeco.questconstructor.views.PublicationView


@InjectViewState
class PublicationPresenter : MvpPresenter<PublicationView>() {
    fun getCopyProject(idProject: Int) {
        viewState.getCopyProject(CopyProjectModel().getCopyProject(idProject))
    }
}