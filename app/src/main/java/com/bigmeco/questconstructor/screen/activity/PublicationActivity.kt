package com.bigmeco.questconstructor.screen.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_publication.*
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.util.Log
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.R
import com.bigmeco.questconstructor.presenter.PublicationPresenter
import com.bigmeco.questconstructor.screen.adapter.StylesPagerAdapter
import com.bigmeco.questconstructor.views.PublicationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.realm.Realm


class PublicationActivity :  MvpAppCompatActivity(), PublicationView {



    @InjectPresenter
    lateinit var publicationPresenter: PublicationPresenter

    @ProvidePresenter
    fun providePublicationPresenter(): PublicationPresenter {
        return PublicationPresenter()
    }

    val realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publication)
        tabDots.setupWithViewPager(pager, true)
        if (pager != null) {
            pager.adapter = StylesPagerAdapter(this)
        }

        publicationPresenter.getCopyProject(intent.getIntExtra("idProject", 0))



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
    override fun getCopyProject(objectProject: ObjectProject) {
        val fireStoreDataBase = FirebaseFirestore.getInstance()
        val uid = let { FirebaseAuth.getInstance().currentUser!!.uid }
        val db = FirebaseFirestore.getInstance();

        textOk.setOnClickListener {
            fireStoreDataBase.collection("users")
                    .document(uid)
                    .collection("test2")
                    .document()
                    .set(objectProject)
                    .addOnSuccessListener { aVoid ->
                        Log.i("WORK", "Works ")
                    }
                    .addOnFailureListener { exception ->
                        Log.i("Error", "Error occurred during a personal data being submitted in database $exception")
                    }
            val docRef = db.collection("users").document("1pi0pMapOXZMDDfg8T5MFZujyw33").collection("test2").document("0XTy0CEbh4fzIfa1tQpu")
            docRef.get().addOnCompleteListener { task ->
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
            }


        }

    }
}
