package com.bigmeco.questconstructor

import io.realm.RealmList

class PojoButton {
    var text: String? = null
    var id: Int? = null
    var status: Boolean? = false
    var button: RealmList<ObjectButton>? = null

}