package com.bigmeco.questconstructor.interfaceModels

import com.bigmeco.questconstructor.data.InfoProject

interface ILoadListGameModel {

    fun setList(updateList: (ArrayList<InfoProject>) -> Unit)

    fun setListGenre(updateList: (ArrayList<InfoProject>) -> Unit, s: String)

    fun setListFilter(updateList: (ArrayList<InfoProject>) -> Unit, s: String, i: Int)
}