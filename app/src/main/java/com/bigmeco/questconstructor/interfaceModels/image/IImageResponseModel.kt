package com.bigmeco.questconstructor.interfaceModels.image

import android.graphics.Bitmap
import com.bigmeco.questconstructor.statements.ImageRespons

interface IImageResponseModel {
      fun getImageResponse(url: String, respons: ImageRespons)
}