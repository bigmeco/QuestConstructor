package com.bigmeco.questconstructor.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.bigmeco.questconstructor.data.ObjectProject


@StateStrategyType(AddToEndStrategy::class)
interface PublicationView : MvpView {
    fun getCopyProject(objectProject: ObjectProject)
    fun releaseOK()
    fun releaseError()
}