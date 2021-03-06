package com.bigmeco.questconstructor.collections

import com.bigmeco.questconstructor.R

enum class ResourcesModel constructor(val layoutResourceId: Int) {
    CYBERPUNK_SCREEN(R.layout.layout_cyberpunk),
    SECOND_SCREEN(R.layout.layout_steampunk),
    FANTASY_SCREEN(R.layout.layout_fantasy),
    HORROR_SCREEN(R.layout.layout_horror)
}