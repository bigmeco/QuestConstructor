package com.bigmeco.questconstructor.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.data.ObjectScreen


@StateStrategyType(AddToEndStrategy::class)
interface CreatorScreenView : MvpView {
    fun getProject(objectProject: ObjectProject)
    fun addScreen(idNewScreen: Int)

}