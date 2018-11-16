package com.bigmeco.questconstructor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager

import kotlinx.android.synthetic.main.activity_publication.*
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults
import com.google.firebase.firestore.DocumentSnapshot
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.DocumentReference




class PublicationActivity : AppCompatActivity() {
    private var idProject = 0


    val realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publication)
        tabDots.setupWithViewPager(pager, true)
        if (pager != null) {
            pager.adapter = StylesPagerAdapter(this)
        }
         val db = FirebaseFirestore.getInstance();

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        idProject = preferences.getInt("idProject", 0)
        var objectProject = realm.copyFromRealm(realm.where(ObjectProject::class.java).equalTo("id", idProject).findAll())

        val uid = let { FirebaseAuth.getInstance().currentUser!!.uid }
        textOk.setOnClickListener {
            val fireStoreDataBase = FirebaseFirestore.getInstance()

            val docRef = db.collection("users").document("1pi0pMapOXZMDDfg8T5MFZujyw33").collection("test2").document("0XTy0CEbh4fzIfa1tQpu")
            docRef.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document!!.exists()) {
                        Log.d("qaqaqaqaqaq", "DocumentSnapshot data: " + document.data!!)
                    } else {
                        Log.d("qaqaqaqaqaq", "No such document")
                    }
                } else {
                    Log.d("qaqaqaqaqaq", "get failed with ", task.exception)
                }
            })

            fireStoreDataBase.collection("users")
                    .document(uid)
                    .collection("test2")
                    .document()
                    .set(objectProject[0]!!)
                    .addOnSuccessListener { aVoid ->
                        Log.i("WORK", "Works ")
                    }
                    .addOnFailureListener { exception ->
                        Log.i("Error", "Error occurred during a personal data being submitted in database $exception")
                    }
        }

        pager.addOnPageChangeListener(object : OnPageChangeListener {


            override fun onPageScrolled(position: Int, positionOffset: Float,
                                        positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                Log.d("wwwwww", position.toString())
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

    }
}
