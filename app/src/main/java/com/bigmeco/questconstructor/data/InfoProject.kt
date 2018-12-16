package com.bigmeco.questconstructor.data

import io.realm.annotations.PrimaryKey

open class InfoProject {
    var name: String? = null
    var genre: String? = null
    var body: String? = null
    var time: String? = null
    @PrimaryKey
    var id: String? = null
}


