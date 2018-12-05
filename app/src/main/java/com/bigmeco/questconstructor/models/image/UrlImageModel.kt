package com.bigmeco.questconstructor.models.image

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Handler
import android.util.Log
import com.bigmeco.questconstructor.interfaceModels.image.IUrlImageModel
import com.bigmeco.questconstructor.presenter.CreatorScreenFrPresenter
import com.squareup.picasso.Picasso
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import java.io.IOException
import kotlin.coroutines.experimental.suspendCoroutine


class UrlImageModel : IUrlImageModel {

    override suspend fun urlToImage(url: String, errorImage: Int): Bitmap? {
        return suspendCoroutine { continuation ->
            try {
                Picasso.get().load(url).error(errorImage).into(object : com.squareup.picasso.Target {
                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        continuation.resume(bitmap)
                        Log.d("eeeeeeeeeee", "onBitmapLoaded")
                    }

                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                        val handler = Handler()
                        handler.postDelayed({ CreatorScreenFrPresenter().getImageResponse(url, errorImage) }, 2000)
                    }

                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
                })
            } catch (e: IOException) {

            }
        }

    }
}