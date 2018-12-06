package com.bigmeco.questconstructor.interfaceModels

import com.bigmeco.questconstructor.data.ObjectProject

interface ICopyProjectModel {
    fun getCopyProject(idProject:Int): ObjectProject
}