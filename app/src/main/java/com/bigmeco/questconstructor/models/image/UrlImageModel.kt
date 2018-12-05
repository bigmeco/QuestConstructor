package com.bigmeco.questconstructor.models.image

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import com.bigmeco.questconstructor.interfaceModels.image.IUrlImageModel
import com.squareup.picasso.Picasso
import java.io.IOException
import kotlin.coroutines.experimental.suspendCoroutine


class UrlImageModel : IUrlImageModel {

    override suspend fun urlToImage(url: String, errorImage: Int): Bitmap? {

        return suspendCoroutine { continuation ->
            try {
                Picasso.get().load(url).into(object : com.squareup.picasso.Target {
                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        continuation.resume(bitmap)
                        Log.d("eeeeeeeeeee","onBitmapLoaded")
                    }

                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                        Log.d("eeeeeeeeeee","onPrepareLoad")
                        continuation.resume(null)

                    }

                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
                })            } catch (e: IOException) {

            }
        }


    }

}