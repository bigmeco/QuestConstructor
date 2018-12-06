package com.bigmeco.questconstructor.models.image

import android.graphics.Bitmap
import com.bigmeco.questconstructor.interfaceModels.image.IImageResponseModel

class ImageResponseModel : IImageResponseModel {
    override suspend fun getImageResponse(url: String, errorImage: Bitmap): Bitmap? {
        val urlImageModel = UrlImageModel()
        return urlImageModel.urlToImage(url, errorImage)
    }
}