package com.bigmeco.questconstructor.data

import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class InfoProject : Serializable {
    var name: String? = null
    var genre: String? = null
    var body: String? = null
    var rating: Int? = null
    var time: String? = null
    @PrimaryKey
    var id: String? = null
}


