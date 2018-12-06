package com.bigmeco.questconstructor.presenter

import android.graphics.Bitmap
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.bigmeco.questconstructor.data.ObjectButton
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.data.ObjectScreen
import com.bigmeco.questconstructor.models.CreatorScreenModel
import com.bigmeco.questconstructor.models.ExitButtonModel
import com.bigmeco.questconstructor.models.image.ImageResponseModel
import com.bigmeco.questconstructor.views.CreatorScreenFrView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import com.bigmeco.questconstructor.models.image.RecordImageModel
import io.realm.Realm


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
                   idScreen: Int, idButton: Int?):ArrayList<ObjectScreen> {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {

            if (idButton != null) {
                objectButtons[idButton].id = idScreen
            }
            objectScreen.body = body
            objectScreen.status = objectScreen.thereIsNull()

            objectScreen.buttons!!.clear()
            objectScreen.buttons!!.addAll(objectButtons)


            for (i in 0 until objectScreen.buttons!!.size) {
                if (objectScreen.buttons!![i]!!.thereIsNull()) {
                    objectScreen.buttons!![i]!!.status = true
                } else {
                    objectScreen.buttons!![i]!!.status = false
                    break
                }
            }
            if (objectButtons.size != 0) {
                for (i in 0 until objectScreen.buttons!!.size) {
                    if (objectScreen.buttons!![i]!!.status!!) {
                        objectScreen.status = true
                    } else {
                        objectScreen.status = false
                        break
                    }
                }
            } else {
                objectScreen.status = false
            }
            if (objectProjects.thereIsNull()) {
                for (i in 0 until objectProjects.screen!!.size) {
                    if (objectProjects.screen!![i]!!.status!!) {
                        objectProjects.status = true
                    } else {
                        objectProjects.status = false
                        break
                    }
                }
            } else {
                objectProjects.status = false
            }
        }
        return ArrayList(objectProjects.screen)
    }

}