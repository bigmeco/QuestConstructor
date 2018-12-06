package com.bigmeco.questconstructor.models.image

import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.data.ObjectScreen
import com.bigmeco.questconstructor.interfaceModels.image.IRecordImageModel
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_creator_screen.*

class RecordImageModel:IRecordImageModel {
    private val realm = Realm.getDefaultInstance()
    override fun recordImageUrl(objectScreen: ObjectScreen?, url: String) {
        realm.executeTransaction {
            objectScreen!!.image = url
        }
    }
}