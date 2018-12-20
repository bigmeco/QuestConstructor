package com.bigmeco.questconstructor.interfaceModels

import com.bigmeco.questconstructor.data.Project

interface ILoadSaveProjectModel {
    fun loadHistory(id: String, loadProject: (Project) -> Unit)
}