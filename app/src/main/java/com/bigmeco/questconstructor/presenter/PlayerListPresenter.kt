package com.bigmeco.questconstructor.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.bigmeco.questconstructor.data.InfoProject
import com.bigmeco.questconstructor.interfaceModels.ILoadListGameModel
import com.bigmeco.questconstructor.models.LoadListGameModel
import com.bigmeco.questconstructor.views.PlayerListView

@InjectViewState
class PlayerListPresenter : MvpPresenter<PlayerListView>() {
    val loadList:ILoadListGameModel = LoadListGameModel()

    fun setList(updateList: (ArrayList<InfoProject>) -> Unit) {
        loadList.setList(updateList)
    }

    fun setListGenre(updateList: (ArrayList<InfoProject>) -> Unit, s: String) {
        loadList.setListGenre(updateList,s)
    }

    fun setListFilter(updateList: (ArrayList<InfoProject>) -> Unit, s: String, i: Int) {
        loadList.setListFilter(updateList,s,i)
    }
}