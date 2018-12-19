package com.bigmeco.questconstructor.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.interfaceModels.IReleaseProjectModel
import com.bigmeco.questconstructor.models.CopyProjectModel
import com.bigmeco.questconstructor.models.ReleaseProjectModel
import com.bigmeco.questconstructor.statements.ReleaseRespons
import com.bigmeco.questconstructor.views.PublicationView


@InjectViewState
class PublicationPresenter : MvpPresenter<PublicationView>() {
    fun getCopyProject(idProject: Int) {

        viewState.getCopyProject(CopyProjectModel().getCopyProject(idProject))
    }

    fun releaseProject(idStyle: Int, objectProject: ObjectProject) {
        val result = ReleaseRespons()
        result.respons = {
            viewState.releaseOK()
        }
        result.respons = {
            viewState.releaseError()
        }

        val  r : IReleaseProjectModel = ReleaseProjectModel()
        r.releaseProject(idStyle, objectProject, result)
    }
}