package com.bigmeco.questconstructor.interfaceModels

import com.bigmeco.questconstructor.data.ObjectProject

interface IAddScreenModel {
    fun addScreen(objectProject: ObjectProject): Int?
}