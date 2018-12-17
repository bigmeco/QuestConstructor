package com.bigmeco.questconstructor.models

import android.util.Log
import com.bigmeco.questconstructor.data.InfoProject
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.interfaceModels.IReleaseProjectModel
import com.bigmeco.questconstructor.statements.ReleaseRespons
import com.google.firebase.firestore.FirebaseFirestore

class ReleaseProjectModel : IReleaseProjectModel {

    override fun releaseProject(idStyle: Int, objectProject: ObjectProject, result: ReleaseRespons) {
        val fireStoreDataBase = FirebaseFirestore.getInstance()

        objectProject.idStyle = idStyle
        val infoProject = InfoProject()
        val id = fireStoreDataBase.collection("quests").document().id
        infoProject.id = id
        infoProject.rating = 0
        infoProject.body = objectProject.body
        infoProject.name = objectProject.name
        infoProject.genre = objectProject.genre
        infoProject.time = objectProject.time
        fireStoreDataBase.collection("questBody")
                .document(id)
                .set(objectProject)
                .addOnSuccessListener { aVoid ->
                    Log.i("WORK", "Works ")
                    result.respons?.invoke()
                }
                .addOnFailureListener { exception ->
                    Log.i("Error", "Error occurred during a personal data being submitted in database $exception")
                    result.error?.invoke()

                }
        fireStoreDataBase.collection("quests").document(id).set(infoProject)

    }

}