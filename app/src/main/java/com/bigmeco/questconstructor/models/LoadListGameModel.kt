package com.bigmeco.questconstructor.models

import com.bigmeco.questconstructor.data.InfoProject
import com.bigmeco.questconstructor.interfaceModels.ILoadListGameModel
import com.google.firebase.firestore.FirebaseFirestore

class LoadListGameModel: ILoadListGameModel {
    val fireStoreDataBase = FirebaseFirestore.getInstance()

    override fun setList(updateList: (ArrayList<InfoProject>) -> Unit) {
        fireStoreDataBase.collection("quests")
                .get().addOnCompleteListener {
                    val lecturesPojos = ArrayList<InfoProject>()
                    for (document in it.result!!) {
                        val myObject = document.toObject(InfoProject::class.java)
                        lecturesPojos.add(myObject)
                    }
                    updateList(lecturesPojos)
                }
    }

    override fun setListGenre(updateList: (ArrayList<InfoProject>) -> Unit, s: String) {
        fireStoreDataBase.collection("quests")
                .whereEqualTo("genre", s)
                .get().addOnCompleteListener {
                    val lecturesPojos = ArrayList<InfoProject>()
                    for (document in it.result!!) {
                        val myObject = document.toObject(InfoProject::class.java)
                        lecturesPojos.add(myObject)
                    }
                    updateList(lecturesPojos)
                }
    }

    override fun setListFilter(updateList: (ArrayList<InfoProject>) -> Unit, s: String, i: Int) {
        fireStoreDataBase.collection("quests")
                .whereEqualTo("time", s)
                .whereEqualTo("rating", i)
                .get().addOnCompleteListener {
                    val lecturesPojos = ArrayList<InfoProject>()
                    for (document in it.result!!) {
                        val myObject = document.toObject(InfoProject::class.java)
                        lecturesPojos.add(myObject)
                    }
                    updateList(lecturesPojos)
                }
    }
}