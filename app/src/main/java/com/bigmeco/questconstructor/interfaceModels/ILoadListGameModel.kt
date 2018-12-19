package com.bigmeco.questconstructor.interfaceModels

import com.bigmeco.questconstructor.data.InfoProject

interface ILoadListGameModel {

    fun setList(updateList: (ArrayList<InfoProject>,save:Boolean) -> Unit)

    fun setListGenre(updateList: (ArrayList<InfoProject>,save:Boolean) -> Unit, s: String)

    fun setListFilter(updateList: (ArrayList<InfoProject>,save:Boolean) -> Unit, s: String, i: Int)
}