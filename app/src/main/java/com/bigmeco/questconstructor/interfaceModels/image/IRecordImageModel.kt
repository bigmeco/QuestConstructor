package com.bigmeco.questconstructor.interfaceModels.image

import com.bigmeco.questconstructor.data.ObjectScreen

interface IRecordImageModel {
   fun recordImageUrl(objectScreen: ObjectScreen?, url: String)
}