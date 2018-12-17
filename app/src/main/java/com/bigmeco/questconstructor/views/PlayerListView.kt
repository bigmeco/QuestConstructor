package com.bigmeco.questconstructor.views

import com.arellomobile.mvp.MvpView

interface PlayerListView:MvpView {
    fun getList()

    fun getListGenre(s: String)

    fun getListFilter(s: String, i: Int)
}