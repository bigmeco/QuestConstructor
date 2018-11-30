package com.bigmeco.questconstructor.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.bigmeco.questconstructor.models.CreatorModel
import com.bigmeco.questconstructor.views.CreatorView

@InjectViewState
class CreatorPresenter : MvpPresenter<CreatorView>() {

    fun getProjects() {
        viewState.getProjects(CreatorModel().test())
    }
}
