package com.bigmeco.questconstructor.data

import io.realm.RealmList
import io.realm.RealmObject


open class Screen  {
    var body: String?  =null
    var image: String?  =null
    var id: Int? =null
    var buttons: ArrayList<Button>?  =null
    var status: Boolean? = false

}

//data class ObjectScreen (
//    var body: String? ,
//    var image: String? = null,
//    var id: Int?,
//    var buttons: List<ObjectButton>?
//)