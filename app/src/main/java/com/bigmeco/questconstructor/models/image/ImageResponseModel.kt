package com.bigmeco.questconstructor.models.image

import android.graphics.Bitmap
import com.bigmeco.questconstructor.interfaceModels.image.IImageResponseModel
import com.bigmeco.questconstructor.statements.ImageRespons

class  ImageResponseModel : IImageResponseModel {
    override fun getImageResponse(url: String,respons:ImageRespons) {
        val urlImageModel = UrlImageModel()
        urlImageModel.urlToImage(url,respons)
    }


}