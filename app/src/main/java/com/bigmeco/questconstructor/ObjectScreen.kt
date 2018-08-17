package com.bigmeco.questconstructor

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class ObjectScreen : RealmObject() {
    var body: String?  =null
    var image: String?  =null
    var id: Int? =null
    var buttons: RealmList<ObjectButton>?  =null
    var status: Boolean? = false


    fun thereIsNull():Boolean= body != null && id != null && buttons != null
}

//data class ObjectScreen (
//    var body: String? ,
//    var image: String? = null,
//    var id: Int?,
//    var buttons: List<ObjectButton>?
//)