package com.bigmeco.questconstructor.models.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Handler
import android.util.Log
import com.bigmeco.questconstructor.QuestConstructorApplication
import com.bigmeco.questconstructor.interfaceModels.image.IUrlImageModel
import com.bigmeco.questconstructor.presenter.CreatorScreenFrPresenter
import com.bigmeco.questconstructor.statements.ImageRespons
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.IOException
import kotlin.coroutines.experimental.suspendCoroutine


class UrlImageModel : IUrlImageModel {

    var target: Target? = null
    override fun urlToImage(url: String, respons: ImageRespons) {
        if (url != null && url != "") {
            target = object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    try {
                        bitmap?.let { respons.respons!!.invoke(it) }
                        Log.d("eeeeeeeeeee", "onBitmapLoaded")
                    } catch (e: NullPointerException) {
                    }

                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    val handler = Handler()
                    handler.postDelayed({ Picasso.get().load(url).into(target!!) }, 2000)
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    respons.errorImage!!.invoke()
                }

            }
            Picasso.get().load(url).into(target!!)
        }
    }
}