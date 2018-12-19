package com.bigmeco.questconstructor.models

import android.util.Log
import com.bigmeco.questconstructor.data.InfoProject
import com.bigmeco.questconstructor.data.MyProject
import com.bigmeco.questconstructor.interfaceModels.ILoadListGameModel
import com.google.firebase.firestore.FirebaseFirestore
import io.realm.Realm

class LoadListGameModel : ILoadListGameModel {
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
        if (s != "Мои") {
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
        } else {
            val realm: Realm = Realm.getDefaultInstance()
            val myProject = ArrayList<MyProject>()
        Log.e("fdddddddddddd",realm.where(MyProject::class.java).findAll()!!.toString())
            myProject.addAll(realm.where(MyProject::class.java).findAll()!!)
            Log.e("fdddddddddddd",myProject.toString())

            val infoProjects = ArrayList<InfoProject>()
            for (myPr in myProject.iterator()){
                val infoProject= InfoProject()
                infoProject.body = myPr.body
                infoProject.genre = myPr.genre
                infoProject.id= myPr.id
                infoProject.name = myPr.name
                infoProject.rating = 8
                infoProject.time = myPr.time
                infoProjects.add(infoProject)
            }
            updateList(infoProjects)


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