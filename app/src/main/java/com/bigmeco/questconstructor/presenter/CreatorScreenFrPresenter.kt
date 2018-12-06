package com.bigmeco.questconstructor.presenter

import android.graphics.Bitmap
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.bigmeco.questconstructor.data.ObjectScreen
import com.bigmeco.questconstructor.models.CreatorScreenModel
import com.bigmeco.questconstructor.models.image.ImageResponseModel
import com.bigmeco.questconstructor.views.CreatorScreenFrView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import com.bigmeco.questconstructor.models.image.RecordImageModel


@InjectViewState
class CreatorScreenFrPresenter : MvpPresenter<CreatorScreenFrView>() {

    fun getProject(idProject: Int) {
        val creatorScreenModel = CreatorScreenModel()
        creatorScreenModel.getProject(idProject)?.let { viewState.getProject(it) }
    }

    fun getImageResponse(url: String, errorImage: Bitmap) {
        val imageResponseModel = ImageResponseModel()
          launch (UI){
                  viewState.getImageResponse( imageResponseModel.getImageResponse(url, errorImage)!!)
            }

    }
    fun setImageResponse(objectScreen: ObjectScreen?, url: String) {
       val recordImageModel =RecordImageModel()
        recordImageModel.recordImageUrl(objectScreen,url)
        viewState.readImageResponse()
    }
}