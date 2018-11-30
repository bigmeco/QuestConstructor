package com.bigmeco.questconstructor.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface SplashView : MvpView {
    fun auth(auth: Boolean)


}