package com.bigmeco.questconstructor.interfaceModels.image

import android.graphics.Bitmap

interface IImageResponseModel {
     suspend fun getImageResponse(url: String, errorImage: Bitmap): Bitmap?
}