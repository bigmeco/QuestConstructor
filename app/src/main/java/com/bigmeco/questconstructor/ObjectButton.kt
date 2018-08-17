package com.bigmeco.questconstructor

import io.realm.RealmList
import io.realm.RealmObject


open class ObjectButton : RealmObject() {
    var text: String? = null
    var id: Int? = null
    var status: Boolean? = false


   fun thereIsNull(): Boolean = text != null && id != null

}
//
//class ObjectButton {
//    var text: String? = null
//    var id: Int? = null
//
//}