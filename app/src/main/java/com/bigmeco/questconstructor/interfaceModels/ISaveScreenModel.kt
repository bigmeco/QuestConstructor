package com.bigmeco.questconstructor.interfaceModels

import com.bigmeco.questconstructor.data.ObjectButton
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.data.ObjectScreen

interface ISaveScreenModel {
    fun fillSaveScreen(objectProjects: ObjectProject, objectScreen: ObjectScreen,
                       objectButtons: ArrayList<ObjectButton>, body: String,
                       idScreen: Int, idButton: Int?):ArrayList<ObjectScreen>
}