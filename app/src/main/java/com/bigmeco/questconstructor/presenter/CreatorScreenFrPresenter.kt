package com.bigmeco.questconstructor.presenter

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.bigmeco.questconstructor.models.CreatorScreenModel
import com.bigmeco.questconstructor.models.image.ImageResponseModel
import com.bigmeco.questconstructor.views.CreatorScreenFrView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import android.support.v4.os.HandlerCompat.postDelayed
import android.util.Log


@InjectViewState
class CreatorScreenFrPresenter : MvpPresenter<CreatorScreenFrView>() {

    fun getProject(idProject: Int) {
        val creatorScreenModel = CreatorScreenModel()
        creatorScreenModel.getProject(idProject)?.let { viewState.getProject(it) }
    }

    fun getImageResponse(url: String, errorImage: Int) {
        val imageResponseModel = ImageResponseModel()
          launch (UI){
              if (imageResponseModel.getImageResponse(url, errorImage)!=null){
                  Log.d("eeeeeeeeeee","da")
                  viewState.getImageResponse( imageResponseModel.getImageResponse(url, errorImage)!!)
              }else {
                  Log.d("eeeeeeeeeee","ne")

                  val handler = Handler()
                  handler.postDelayed( {  getImageResponse(url,errorImage)}, 2000)

              }

            }

    }
}