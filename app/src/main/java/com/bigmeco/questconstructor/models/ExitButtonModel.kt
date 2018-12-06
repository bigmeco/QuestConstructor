package com.bigmeco.questconstructor.models

import com.bigmeco.questconstructor.data.ObjectButton
import com.bigmeco.questconstructor.interfaceModels.IExitButtonModel
import com.bigmeco.questconstructor.statements.ScreenConstant

class ExitButtonModel:IExitButtonModel {
    override fun addButtonExit(objectButtons: ArrayList<ObjectButton>): ArrayList<ObjectButton> {
        val objectButton = ObjectButton()
        objectButton.id = ScreenConstant.SCREEN_EXIT
        objectButton.status = true
        objectButtons.add(objectButton)
        return objectButtons
    }
}