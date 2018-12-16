package com.bigmeco.questconstructor.data

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ObjectProject : RealmObject() {

    var name: String? =null
    var genre: String? =null
    var body: String? =null
    var time: String? =null
    @PrimaryKey
    var id: Int? =null
    var idStyle: Int? =null
    var status: Boolean? = false
    var screen: RealmList<ObjectScreen>? =null


    fun thereIsNull(): Boolean = name != null && body != null && name != "" && body != ""


}


//data class ObjectProject(var name: String? ,
//                    var genre: String? ,
//                    var body: String? ,
//                    var time: String? ,
//                    var id: Int? ,
//                    var screen: List<ObjectScreen>? ) {
//
//}