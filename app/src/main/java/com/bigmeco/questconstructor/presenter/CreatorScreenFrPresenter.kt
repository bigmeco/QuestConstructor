package com.bigmeco.questconstructor.presenter

import android.graphics.Bitmap
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.bigmeco.questconstructor.data.ObjectButton
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.data.ObjectScreen
import com.bigmeco.questconstructor.models.CreatorScreenModel
import com.bigmeco.questconstructor.models.ExitButtonModel
import com.bigmeco.questconstructor.models.SaveScreenModel
import com.bigmeco.questconstructor.models.image.ImageResponseModel
import com.bigmeco.questconstructor.models.image.RecordImageModel
import com.bigmeco.questconstructor.views.CreatorScreenFrView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch


@InjectViewState
class CreatorScreenFrPresenter : MvpPresenter<CreatorScreenFrView>() {

    fun getProject(idProject: Int) {
        val creatorScreenModel = CreatorScreenModel()
        creatorScreenModel.getProject(idProject)?.let { viewState.getProject(it) }
    }

    fun getImageResponse(url: String, errorImage: Bitmap) {
        val imageResponseModel = ImageResponseModel()
        launch(UI) {
            viewState.getImageResponse(imageResponseModel.getImageResponse(url, errorImage)!!)
        }
    }


    fun setImageResponse(objectScreen: ObjectScreen?, url: String) {
        val recordImageModel = RecordImageModel()
        recordImageModel.recordImageUrl(objectScreen, url)
        viewState.readImageResponse()
    }

    fun addButtonExit(objectButtons: ArrayList<ObjectButton>) {
        viewState.resultCreationButton(ExitButtonModel().addButtonExit(objectButtons))
    }

    fun saveScreen(objectProjects: ObjectProject, objectScreen: ObjectScreen,
                   objectButtons: ArrayList<ObjectButton>, body: String,
                   idScreen: Int, idButton: Int?): ArrayList<ObjectScreen> {
        val saveScreenModel = SaveScreenModel()
        return saveScreenModel.fillSaveScreen(objectProjects, objectScreen, objectButtons, body, idScreen, idButton)
    }

}