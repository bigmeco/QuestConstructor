package com.bigmeco.questconstructor.views

import android.graphics.Bitmap
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.bigmeco.questconstructor.data.ObjectProject

@StateStrategyType(AddToEndStrategy::class)
interface CreatorScreenFrView : MvpView {
    fun getProject(objectProject: ObjectProject)
    fun getImageResponse(image:Bitmap)

}