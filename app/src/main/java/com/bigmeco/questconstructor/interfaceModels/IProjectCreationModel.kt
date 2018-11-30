package com.bigmeco.questconstructor.interfaceModels

import com.bigmeco.questconstructor.data.ObjectProject

interface IProjectCreationModel {
    fun getStandardProject(objectProject: ObjectProject): ObjectProject

}