package com.bigmeco.questconstructor.interfaceModels

import com.bigmeco.questconstructor.data.ObjectButton

interface IExitButtonModel {
    fun addButtonExit(objectButtons: ArrayList<ObjectButton>): ArrayList<ObjectButton>
}