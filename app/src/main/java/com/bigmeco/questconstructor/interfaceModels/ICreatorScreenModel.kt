package com.bigmeco.questconstructor.interfaceModels

import com.bigmeco.questconstructor.data.ObjectProject

interface ICreatorScreenModel {
    fun getProject(idProject:Int): ObjectProject?
}