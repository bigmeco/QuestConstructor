package com.bigmeco.questconstructor.collections

import com.bigmeco.questconstructor.R

enum class RatingModel constructor( val image: Int,val size:Int) {
    STAR1(R.drawable.anim_star1,0),
    STAR15(R.drawable.anim_star15,1),
    STAR2(R.drawable.anim_star2,2),
    STAR25(R.drawable.anim_star25,3),
    STAR3(R.drawable.anim_star3,4),
    STAR35(R.drawable.anim_star35,5),
    STAR4(R.drawable.anim_star4,6),
    STAR45(R.drawable.anim_star45,7),
    STAR5(R.drawable.anim_star5,8),
}