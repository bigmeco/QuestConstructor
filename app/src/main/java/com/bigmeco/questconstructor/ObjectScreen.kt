package com.bigmeco.questconstructor

data class ObjectScreen (
    var body: String? ,
    var image: String? = null,
    var id: Int?,
    var buttons: List<ObjectButton>?
)