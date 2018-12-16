package com.bigmeco.questconstructor.interfaceModels.image

import android.graphics.Bitmap
import com.bigmeco.questconstructor.models.image.UrlImageModel
import com.bigmeco.questconstructor.statements.ImageRespons

interface IUrlImageModel {
    fun urlToImage(url: String, respons: ImageRespons)

}