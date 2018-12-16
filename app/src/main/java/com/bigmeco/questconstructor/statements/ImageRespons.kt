package com.bigmeco.questconstructor.statements

import android.graphics.Bitmap

class ImageRespons {

    var respons: ((Bitmap) -> Unit)? = null
    var errorImage: (() -> Unit)? = null
}