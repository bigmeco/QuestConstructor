package com.bigmeco.questconstructor.data

import io.realm.RealmList
import io.realm.RealmObject


open class MyButton : RealmObject() {
    var text: String? = null
    var id: Int? = null
    var status: Boolean? = false


   fun thereIsNull(): Boolean = text != null && id != null && text != ""

}
//
//class ObjectButton {
//    var text: String? = null
//    var id: Int? = null
//
//}