package com.bigmeco.questconstructor.models

import android.util.Log
import com.bigmeco.questconstructor.data.InfoProject
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.interfaceModels.IReleaseProjectModel
import com.bigmeco.questconstructor.statements.ReleaseRespons
import com.google.firebase.firestore.FirebaseFirestore
import io.realm.Realm

class ReleaseProjectModel : IReleaseProjectModel {

    override fun releaseProject(idStyle: Int, objectProject: ObjectProject, result: ReleaseRespons) {
        val fireStoreDataBase = FirebaseFirestore.getInstance()

        objectProject.idStyle = idStyle
        val infoProject = InfoProject()

        infoProject.rating = 0
        infoProject.body = objectProject.body
        infoProject.name = objectProject.name
        infoProject.genre = objectProject.genre
        infoProject.time = objectProject.time
        val realm = Realm.getDefaultInstance()
        val prKey = CreatorScreenModel().getProject(objectProject.id!!)!!.releaseKey
        Log.e("dfghdfgdfgdfgdf",prKey+"  dxcgbxcvg")

        if (prKey == "") {
            val id = fireStoreDataBase.collection("quests").document().id
            infoProject.id = id
            realm.executeTransaction {
                CreatorScreenModel().getProject(objectProject.id!!)!!.releaseKey = id
                objectProject.releaseKey = id
            }
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
        } else {
            fireStoreDataBase.collection("questBody")
                    .document(prKey)
                    .set(objectProject)
                    .addOnSuccessListener { aVoid ->
                        Log.i("WORK", "Works ")
                        result.respons?.invoke()
                    }
                    .addOnFailureListener { exception ->
                        Log.i("Error", "Error occurred during a personal data being submitted in database $exception")
                        result.error?.invoke()

                    }
            fireStoreDataBase.collection("quests").document(prKey).set(infoProject)
        }
    }

}