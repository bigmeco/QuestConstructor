package com.bigmeco.questconstructor.interfaceModels

import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.statements.ReleaseRespons

interface IReleaseProjectModel {
    fun releaseProject(idStyle: Int, objectProject: ObjectProject, result: ReleaseRespons)  {

    }

}