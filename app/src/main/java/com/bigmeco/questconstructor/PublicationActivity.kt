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
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        idProject = preferences.getInt("idProject", 0)
        var objectProject = realm.copyFromRealm (realm.where(ObjectProject::class.java).equalTo("id", idProject).findAll())

        val uid = let { FirebaseAuth.getInstance().currentUser!!.uid }
textOk.setOnClickListener {
    val fireStoreDataBase = FirebaseFirestore.getInstance()


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
                Log.d("wwwwww",position.toString())
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

    }
}
