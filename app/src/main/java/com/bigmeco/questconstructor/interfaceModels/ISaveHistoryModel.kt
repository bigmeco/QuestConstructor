package com.bigmeco.questconstructor.interfaceModels

import com.bigmeco.questconstructor.data.Project

interface ISaveHistoryModel {
    fun saveHistory(id: String, project: Project)
}