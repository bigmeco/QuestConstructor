package com.bigmeco.questconstructor.interfaceModels.image

import android.graphics.Bitmap
import com.bigmeco.questconstructor.models.image.UrlImageModel

interface IUrlImageModel {
    suspend  fun urlToImage(url: String, errorImage: Bitmap): Bitmap?

}