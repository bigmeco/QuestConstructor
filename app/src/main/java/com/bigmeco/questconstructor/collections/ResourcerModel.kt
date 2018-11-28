package com.bigmeco.questconstructor.collections

import com.bigmeco.questconstructor.R

enum class ResourcesModel  constructor( val layoutResourceId: Int) {
    FIRST_SCREEN(R.layout.layout_cyberpunk),
    SECOND_SCREEN(R.layout.layout_steampunk),
    THIRD_SCREEN(R.layout.item_steampunk)
}