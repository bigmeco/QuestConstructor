package com.bigmeco.questconstructor.interfaceModels

import com.bigmeco.questconstructor.data.Project

interface IDownloadProjectModel {
    fun downloadProject(id: String, loadScreen: (Project) -> Unit)
}