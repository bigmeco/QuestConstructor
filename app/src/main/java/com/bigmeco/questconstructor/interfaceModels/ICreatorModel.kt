package com.bigmeco.questconstructor.interfaceModels

import com.bigmeco.questconstructor.data.ObjectProject

interface ICreatorModel {
    fun getProjects(): ArrayList<ObjectProject>

}