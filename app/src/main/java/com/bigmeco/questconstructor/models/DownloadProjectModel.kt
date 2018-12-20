package com.bigmeco.questconstructor.models

import com.bigmeco.questconstructor.data.Project
import com.bigmeco.questconstructor.interfaceModels.IDownloadProjectModel
import com.google.firebase.firestore.FirebaseFirestore

class DownloadProjectModel: IDownloadProjectModel {
    override fun downloadProject(id: String, loadScreen: ( Project) -> Unit) {
        val fireStoreDataBase = FirebaseFirestore.getInstance()

        fireStoreDataBase.collection("questBody").document(id)
                .get().addOnCompleteListener { it ->
                    val document = it.result
                   loadScreen.invoke(document!!.toObject(Project::class.java)!!)
                }
    }

}