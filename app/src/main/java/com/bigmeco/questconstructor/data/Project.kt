package com.bigmeco.questconstructor.data

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Project {

    var name: String? =null
    var genre: String? =null
    var body: String? =null
    var time: String? =null
    @PrimaryKey
    var id: Int? =null
    var idStyle: Int? =null
    var status: Boolean? = false
    var screen: ArrayList<Screen>? =null




}


//data class ObjectProject(var name: String? ,
//                    var genre: String? ,
//                    var body: String? ,
//                    var time: String? ,
//                    var id: Int? ,
//                    var screen: List<ObjectScreen>? ) {
//
//}